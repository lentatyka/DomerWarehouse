package com.lentatyka.domerwarehouse.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class LoginViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> get() = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> get() = _passwordError

    private val _comfirmPasswordError = MutableLiveData<String>()
    val confirmPasswordError: LiveData<String> get() = _comfirmPasswordError

    fun signIn(email: String, password: String) {

    }

    fun signUp(email: String, password: String, confirmPassword: String) {

    }

}