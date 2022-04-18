package com.lentatyka.domerwarehouse.domain.login

import com.lentatyka.domerwarehouse.common.Response
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUseCase<UserInfo> {

    override fun invoke(email: String, password: String) = flow{
        emit(Response.Loading)
        try {
            emit(Response.Success(UserInfo("123Dw2S#")))
        }catch (e: Exception){
            //todo temp solution
            emit(Response.Error(e.localizedMessage ?: "error..."))
        }
    }
}