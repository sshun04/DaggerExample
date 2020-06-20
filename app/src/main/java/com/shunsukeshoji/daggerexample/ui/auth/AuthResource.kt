package com.shunsukeshoji.daggerexample.ui.auth

enum class AuthStatus {
    AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
}

// NOTE: Publisherである事を明示するためにoutをつける必要がある
class AuthResource<out T>(
    val status: AuthStatus,
    val data: T?,
    val message: String?

) {

    companion object {
        fun <T> authenticated(data: T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.AUTHENTICATED,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): AuthResource<T> {
            return AuthResource(
                AuthStatus.LOADING,
                data,
                null
            )
        }

        fun <T> logout(): AuthResource<T> {
            return AuthResource(
                AuthStatus.NOT_AUTHENTICATED,
                null,
                null
            )
        }
    }

}