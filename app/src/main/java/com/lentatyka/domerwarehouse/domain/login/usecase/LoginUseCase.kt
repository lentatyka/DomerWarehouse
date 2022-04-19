package com.lentatyka.domerwarehouse.domain.login.usecase

import com.lentatyka.domerwarehouse.common.Response
import kotlinx.coroutines.flow.Flow

interface LoginUseCase<T> {
    operator fun invoke(email: String, password: String): Flow<Response<T>>
}