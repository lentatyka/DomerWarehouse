package com.lentatyka.domerwarehouse.presentation.login

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.domain.login.LogInUseCase
import com.lentatyka.domerwarehouse.domain.login.SignUpUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.regex.Matcher
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val app: Application,
    private val logInUseCase: LogInUseCase,
    private val signUpUseCase: SignUpUseCase
) : AndroidViewModel(app) {

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> get() = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> get() = _passwordError

    private val _comfirmPasswordError = MutableLiveData<String>()
    val confirmPasswordError: LiveData<String> get() = _comfirmPasswordError

    fun signIn(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            logInUseCase(email, password).onEach {
                //collect
            }.launchIn(viewModelScope)
        }
    }

    fun signUp(email: String, password: String, confirmPassword: String) {
        if (isEmailValid(email) &&
            isPasswordValid(password) &&
            isConfirmPasswordValid(password, confirmPassword)
        ) {
            signUpUseCase(email, password).onEach {
                //collect
            }.launchIn(viewModelScope)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = app.getString(R.string.bad_email)
            false
        } else
            true
    }

    private fun isPasswordValid(password: String): Boolean {
        return if (!TextUtils.isEmpty(password)) {
            _passwordError.value = app.getString(R.string.empty_password)
            false
        } else
            true
    }

    private fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean {
        return if (password != confirmPassword) {
            _comfirmPasswordError.value = app.getString(R.string.bad_confirm_password)
            false
        } else
            true
    }

}