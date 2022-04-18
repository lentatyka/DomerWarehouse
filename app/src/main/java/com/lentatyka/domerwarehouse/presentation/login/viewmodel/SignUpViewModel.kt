package com.lentatyka.domerwarehouse.presentation.login.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.view.View.OnFocusChangeListener
import com.lentatyka.domerwarehouse.di.login.ActivityScope
import com.lentatyka.domerwarehouse.domain.login.SignUpUseCase
import javax.inject.Inject


@ActivityScope
class SignUpViewModel @Inject constructor(
    signUpUseCase: SignUpUseCase
) : LoginViewModel(signUpUseCase) {

    private val _confirmPasswordError = MutableLiveData<Boolean>()
    val confirmPasswordError: LiveData<Boolean> get() = _confirmPasswordError

    fun signUp(email: String, password: String, confirmPassword: String) {
        if (isEmailValid(email) &&
            isPasswordValid(password) &&
            isConfirmPasswordValid(password, confirmPassword)
        ) {
            login(email, password)
        }
    }

    fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean {
        return if (password != confirmPassword) {
            _confirmPasswordError.value = true
            false
        } else
            true
    }

    fun getConfirmPasswordFocusChangeListener(): OnFocusChangeListener {
        return OnFocusChangeListener { _, hasFocus: Boolean ->
            if (hasFocus) {
                _confirmPasswordError.value = false
            }
        }
    }
}