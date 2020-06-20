package com.shunsukeshoji.daggerexample.di

import com.shunsukeshoji.daggerexample.di.auth.AuthModule
import com.shunsukeshoji.daggerexample.di.auth.AuthScope
import com.shunsukeshoji.daggerexample.di.auth.AuthViewModelsModule
import com.shunsukeshoji.daggerexample.di.main.*
import com.shunsukeshoji.daggerexample.ui.auth.AuthActivity
import com.shunsukeshoji.daggerexample.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class, MainViewModelsModule::class, MainModule::class, PostAssistedInjectModule::class])
    abstract fun contributeMainActivity(): MainActivity
}