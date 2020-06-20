package com.shunsukeshoji.daggerexample.di.main

import com.shunsukeshoji.daggerexample.models.Post
import com.shunsukeshoji.daggerexample.ui.main.posts.PostItem
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.Provides

@AssistedModule
@Module(includes = [AssistedInject_PostAssistedInjectModule::class])
interface PostAssistedInjectModule