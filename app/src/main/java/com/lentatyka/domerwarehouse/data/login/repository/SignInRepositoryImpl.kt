package com.lentatyka.domerwarehouse.data.login.repository

import com.lentatyka.domerwarehouse.data.login.network.LoginFirebaseApi
import com.lentatyka.domerwarehouse.di.login.SignIn
import com.lentatyka.domerwarehouse.domain.login.repository.LoginRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
     @SignIn private val firebase: LoginFirebaseApi
):LoginRepository {
    override suspend fun invoke(email: String, password: String) {
        firebase(email, password)
    }
}