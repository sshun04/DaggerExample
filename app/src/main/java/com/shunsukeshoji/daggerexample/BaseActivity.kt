package com.shunsukeshoji.daggerexample

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.observe
import com.shunsukeshoji.daggerexample.ui.auth.AuthActivity
import com.shunsukeshoji.daggerexample.ui.auth.AuthStatus
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "BaseActivity"
    }

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.authUser().observe(this) {
            when (it.status) {
                AuthStatus.LOADING -> {
                }
                AuthStatus.AUTHENTICATED -> {
                    Log.d(TAG, "onChanged: LOGIN SUCCESS: ${it.data}")

                }
                AuthStatus.ERROR -> {
                    Toast.makeText(
                        this,
                        "${it.message} did you enter a number between 1 and 10?",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                AuthStatus.NOT_AUTHENTICATED -> {
                    navLoginScreen()
                }
            }
        }
    }


    private fun navLoginScreen() {
        AuthActivity.start(this)
        finish()
    }

}