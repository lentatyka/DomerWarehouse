package com.lentatyka.domerwarehouse.data

import com.lentatyka.domerwarehouse.domain.login.LoginRepository

class LoginRepositoryImpl:LoginRepository {
    override suspend fun logIn(email: String, password: String): UserInfoDto {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(email: String, password: String): UserInfoDto {
        TODO("Not yet implemented")
    }
}