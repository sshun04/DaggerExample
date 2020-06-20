package com.shunsukeshoji.daggerexample.di.auth

import androidx.lifecycle.ViewModel
import com.shunsukeshoji.daggerexample.di.ViewModelKey
import com.shunsukeshoji.daggerexample.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel):ViewModel
}