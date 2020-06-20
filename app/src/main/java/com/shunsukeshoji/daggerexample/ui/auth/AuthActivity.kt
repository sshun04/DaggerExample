package com.shunsukeshoji.daggerexample.ui.auth

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.bumptech.glide.RequestManager
import com.shunsukeshoji.daggerexample.R
import com.shunsukeshoji.daggerexample.models.User
import com.shunsukeshoji.daggerexample.ui.main.MainActivity
import com.shunsukeshoji.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "AuthActivity"

        fun start(context: Context) {
            val intent = createIntent(context)
            context.startActivity(intent)
        }

        private fun createIntent(context: Context) = Intent(context, AuthActivity::class.java)
    }

    @Inject
    @Named("app_user")
    lateinit var appUser: User

    @Inject
    @Named("auth_user")
    lateinit var authUser: User

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private val viewModel: AuthViewModel by lazy {
        providerFactory.create(AuthViewModel::class.java)
    }

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        requestManager.load(logo).into(login_logo)
        login_button.setOnClickListener {
            attemptLogin()

        }

        // NOTE: Scopeによってインスタンスの生存期間が変わる事を確かめた
        Log.d(TAG + appUser.userName, appUser.toString())
        Log.d(TAG + authUser.userName, authUser.toString())

        viewModel.observeAuthState().observe(this) {
            when (it.status) {
                AuthStatus.LOADING -> {
                    progress_bar.isVisible = true
                }
                AuthStatus.AUTHENTICATED -> {
                    progress_bar.isVisible = false
                    MainActivity.start(this)
                    finish()
                    Log.d(TAG, "onChanged: LOGIN SUCCESS: ${it.data}")

                }
                AuthStatus.NOT_AUTHENTICATED -> {
                    progress_bar.isVisible = false
                }
                AuthStatus.ERROR -> {
                    progress_bar.isVisible = false
                    Toast.makeText(
                        this,
                        "${it.message} did you enter a number between 1 and 10?",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(user_id_input.text.toString())) return
        viewModel.authenticateWithId(user_id_input.text.toString().toInt())
    }
}
