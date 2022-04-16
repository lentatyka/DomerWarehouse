package com.lentatyka.domerwarehouse.domain.login

import com.lentatyka.domerwarehouse.data.UserInfoDto

interface LoginRepository{

    suspend fun logIn(email: String, password: String): UserInfoDto
    suspend fun signUp(email: String, password: String):UserInfoDto

}