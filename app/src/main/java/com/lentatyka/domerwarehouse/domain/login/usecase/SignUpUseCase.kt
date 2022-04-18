package com.lentatyka.domerwarehouse.domain.login.usecase

import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.di.login.SignUp
import com.lentatyka.domerwarehouse.domain.login.UserInfo
import com.lentatyka.domerwarehouse.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    @SignUp private val loginRepository: LoginRepository
) : LoginUseCase<UserInfo> {

    override fun invoke(email: String, password: String) = flow {
        emit(Response.Loading)
        try {
            loginRepository(email, password)
            emit(Response.Success(UserInfo("TEST")))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "temp answer..."))
        }
    }
}