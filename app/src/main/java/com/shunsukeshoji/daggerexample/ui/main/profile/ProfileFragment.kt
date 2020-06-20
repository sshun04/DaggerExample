package com.shunsukeshoji.daggerexample.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import com.shunsukeshoji.daggerexample.R
import com.shunsukeshoji.daggerexample.models.User
import com.shunsukeshoji.daggerexample.ui.auth.AuthStatus
import com.shunsukeshoji.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private val viewModel: ProfileViewModel by lazy {
        providerFactory.create(ProfileViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(requireContext(), "${viewModel.toString()}", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner) {
            when (it.status) {
                AuthStatus.AUTHENTICATED -> {
                    it.data?.let { user ->
                        setUserDetails(user)
                    }
                }
                AuthStatus.ERROR -> {
                    it.message?.let { message ->
                        setErrorDetails(message)
                    }
                }
            }
        }
    }

    private fun setErrorDetails(message: String) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }

    private fun setUserDetails(data: User) {
        email.text = data.email
        username.text = data.userName
        website.text = data.website
    }
}