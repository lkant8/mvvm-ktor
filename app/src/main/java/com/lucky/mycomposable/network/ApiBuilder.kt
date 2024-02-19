package com.lucky.mycomposable.network

abstract class ApiBuilder {

    companion object{
    // manage Authorization and Bearer
        const val AUTHORIZATION ="Authorization"
        private const val BEARER ="Bearer"

        fun authToken(authToken:String?) = "$BEARER $authToken"
    }
}
