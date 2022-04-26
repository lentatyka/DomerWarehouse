package com.lentatyka.domerwarehouse.domain.login


import com.google.firebase.auth.FirebaseUser
import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.data.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LoginInteractor {
    operator fun invoke(email: String, password: String): Flow<Response<FirebaseUser>>

    class Base @Inject constructor(
        private val loginRepository: LoginRepository
    ) : LoginInteractor {
        override fun invoke(email: String, password: String) = flow {
            emit(Response.Loading)
            try {
                emit(
                    Response.Success(
                        loginRepository(email, password)
                    )
                )
            } catch (e: Exception) {
                //todo temp solution
                emit(Response.Error(e.localizedMessage ?: "error..."))
            }
        }
    }
}