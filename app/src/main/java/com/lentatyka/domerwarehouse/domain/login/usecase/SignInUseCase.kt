package com.lentatyka.domerwarehouse.domain.login.usecase

import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.di.login.SignIn
import com.lentatyka.domerwarehouse.domain.login.UserInfo
import com.lentatyka.domerwarehouse.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    @SignIn private val loginRepository: LoginRepository
) : LoginUseCase<UserInfo> {

    override fun invoke(email: String, password: String) = flow{
        emit(Response.Loading)
        try {
            loginRepository(email, password)
            emit(Response.Success(UserInfo("123Dw2S#")))
        }catch (e: Exception){
            //todo temp solution
            emit(Response.Error(e.localizedMessage ?: "error..."))
        }
    }
}