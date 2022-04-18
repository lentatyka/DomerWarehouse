package com.lentatyka.domerwarehouse.data.login.network

interface LoginFirebaseApi {
    suspend operator fun invoke(email: String, password: String)
}