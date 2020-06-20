package com.shunsukeshoji.daggerexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.shunsukeshoji.daggerexample.models.User
import com.shunsukeshoji.daggerexample.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    companion object {
        private const val TAG = "SessionManager"
    }

    private val _cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        _cachedUser.value = AuthResource.loading(null)
        _cachedUser.addSource(source) {
            _cachedUser.value = it
            _cachedUser.removeSource(source)
        }
    }

    fun authUser() :LiveData<AuthResource<User>> = _cachedUser

    fun logOut() {
        _cachedUser.value = AuthResource.logout()
    }
}