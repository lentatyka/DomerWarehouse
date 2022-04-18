package com.lentatyka.domerwarehouse.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.view.View.OnFocusChangeListener
import com.lentatyka.domerwarehouse.domain.login.usecase.SignUpUseCase
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    signUpUseCase: SignUpUseCase
) : LoginViewModel(signUpUseCase) {

    private val _confirmPasswordError = MutableLiveData<Boolean>()
    val confirmPasswordError: LiveData<Boolean> get() = _confirmPasswordError

    init {
        Log.d("TAG", "${confirmPasswordError.value}")
    }

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