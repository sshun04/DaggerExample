package com.shunsukeshoji.daggerexample.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.shunsukeshoji.daggerexample.SessionManager
import com.shunsukeshoji.daggerexample.models.User
import com.shunsukeshoji.daggerexample.network.auth.AuthApi
import com.shunsukeshoji.daggerexample.ui.auth.AuthResource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    fun authenticateWithId(userId: Int) {
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<User>> =
        LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId).onErrorReturn {
                User(
                    -1,
                    "",
                    "",
                    ""
                )
            }.map { user ->
                return@map if (user.id == -1) {
                    AuthResource.error("Could not authenticate", null)
                } else AuthResource.authenticated(user)
            }.subscribeOn(Schedulers.io())
        )


    fun observeAuthState(): LiveData<AuthResource<User>> = sessionManager.authUser()
}