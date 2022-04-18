package com.lentatyka.domerwarehouse.domain.login

import com.lentatyka.domerwarehouse.common.Response
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val loginRepository: LoginRepository
    ):LoginUseCase<UserInfo> {

    override fun invoke(email: String, password: String) = flow {
        emit(Response.Loading)
        try {
            emit(Response.Success(UserInfo("TEST")))
        }catch (e: Exception){
            emit(Response.Error(e.localizedMessage ?: "temp answer..."))
        }
    }
}