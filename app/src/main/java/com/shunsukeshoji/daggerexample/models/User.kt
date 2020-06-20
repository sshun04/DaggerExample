package com.shunsukeshoji.daggerexample.models

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val id: Int,
    @Json(name = "username") val userName: String,
    @Json(name = "email") val email: String,
    @Json(name = "website") val website: String
)