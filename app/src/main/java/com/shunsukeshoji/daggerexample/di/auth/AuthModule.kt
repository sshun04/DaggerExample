package com.shunsukeshoji.daggerexample.di.auth

import com.shunsukeshoji.daggerexample.models.User
import com.shunsukeshoji.daggerexample.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
object AuthModule {

    @AuthScope
    @Provides
    @JvmStatic
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @AuthScope
    @Provides
    @Named("auth_user")
    @JvmStatic
    fun provideUser(): User {
        return User(0, "authScope", "", "")
    }
}