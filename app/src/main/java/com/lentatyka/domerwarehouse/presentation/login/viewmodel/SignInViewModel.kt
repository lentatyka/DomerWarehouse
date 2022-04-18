package com.lentatyka.domerwarehouse.presentation.login.viewmodel

import com.lentatyka.domerwarehouse.domain.login.usecase.SignInUseCase
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    signInUseCase: SignInUseCase
):LoginViewModel(signInUseCase) {

    fun signIn(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password))
            login(email, password)
    }
}