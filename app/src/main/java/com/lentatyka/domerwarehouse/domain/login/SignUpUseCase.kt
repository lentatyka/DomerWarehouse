package com.lentatyka.domerwarehouse.domain.login

import com.lentatyka.domerwarehouse.common.Responce
import kotlinx.coroutines.flow.flow

class SignUpUseCase(private val loginRepository: LoginRepository) {

    operator fun invoke(email: String, password: String) = flow {
        emit(Responce.Loading)
        try {
            //firebase request
        }catch (e: Exception){
            emit(Responce.Error(e.localizedMessage ?: "temp answer..."))
        }
    }
}