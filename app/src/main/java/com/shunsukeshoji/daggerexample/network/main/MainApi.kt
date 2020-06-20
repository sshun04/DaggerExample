package com.shunsukeshoji.daggerexample.network.main

import com.shunsukeshoji.daggerexample.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getPosts(@Query("userId") id: Int): Flowable<List<Post>>
}