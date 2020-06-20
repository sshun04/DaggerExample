package com.shunsukeshoji.daggerexample.di

import androidx.lifecycle.ViewModelProvider
import com.shunsukeshoji.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}