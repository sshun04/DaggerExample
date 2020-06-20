package com.shunsukeshoji.daggerexample.di.main

import com.shunsukeshoji.daggerexample.ui.main.posts.PostsFragment
import com.shunsukeshoji.daggerexample.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostsFragment
}