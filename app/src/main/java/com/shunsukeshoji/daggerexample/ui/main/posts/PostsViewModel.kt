package com.shunsukeshoji.daggerexample.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.shunsukeshoji.daggerexample.SessionManager
import com.shunsukeshoji.daggerexample.models.Post
import com.shunsukeshoji.daggerexample.network.main.MainApi
import com.shunsukeshoji.daggerexample.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

   private val _posts: MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()
    val posts :LiveData<Resource<List<Post>>> = _posts

    fun observePosts(): LiveData<Resource<List<Post>>> {
        _posts.value = Resource.loading(null)

        val source = LiveDataReactiveStreams.fromPublisher(
            mainApi.getPosts(sessionManager.authUser().value?.data?.id ?: 0)
                .onErrorReturn {
                    val post = Post(-1, "", "")
                    listOf(post)
                }.map {
                    return@map if (it.isEmpty() || it[0].id == -1) {
                        Resource.error("Something went wrong", null)
                    } else {
                        Resource.success(it)
                    }

                }.subscribeOn(Schedulers.io())
        )

        _posts.addSource(source) {
            _posts.value = it
            _posts.removeSource(source)
        }

        return _posts
    }
}