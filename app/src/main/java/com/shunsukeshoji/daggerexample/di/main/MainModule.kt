package com.shunsukeshoji.daggerexample.di.main

import com.shunsukeshoji.daggerexample.network.main.MainApi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {

    @MainScope
    @Provides
    @JvmStatic
    fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

    @MainScope
    @Provides
    @JvmStatic
    fun provideGroupAdapter(): GroupAdapter<GroupieViewHolder> = GroupAdapter()
}