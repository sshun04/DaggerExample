package com.shunsukeshoji.daggerexample.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shunsukeshoji.daggerexample.SessionManager
import com.shunsukeshoji.daggerexample.models.User
import com.shunsukeshoji.daggerexample.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun getAuthenticatedUser(): LiveData<AuthResource<User>> = sessionManager.authUser()
}