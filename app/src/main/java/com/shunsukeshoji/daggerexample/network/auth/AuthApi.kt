package com.shunsukeshoji.daggerexample.network.auth

import com.shunsukeshoji.daggerexample.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User>
}