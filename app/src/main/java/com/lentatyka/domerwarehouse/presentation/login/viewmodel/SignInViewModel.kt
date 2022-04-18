package com.lentatyka.domerwarehouse.presentation.login.viewmodel

import com.lentatyka.domerwarehouse.di.login.ActivityScope
import com.lentatyka.domerwarehouse.domain.login.SignInUseCase
import javax.inject.Inject

@ActivityScope
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
):LoginViewModel(signInUseCase) {

    fun signIn(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password))
            login(email, password)
    }
}