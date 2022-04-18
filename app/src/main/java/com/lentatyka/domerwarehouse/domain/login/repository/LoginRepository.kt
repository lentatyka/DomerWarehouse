package com.lentatyka.domerwarehouse.domain.login.repository

interface LoginRepository{
    suspend operator fun invoke(email: String, password: String)
}