package com.shunsukeshoji.daggerexample.di.main

import androidx.lifecycle.ViewModel
import com.shunsukeshoji.daggerexample.di.ViewModelKey
import com.shunsukeshoji.daggerexample.ui.main.posts.PostsViewModel
import com.shunsukeshoji.daggerexample.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostViewModel(profileViewModel: PostsViewModel): ViewModel
}