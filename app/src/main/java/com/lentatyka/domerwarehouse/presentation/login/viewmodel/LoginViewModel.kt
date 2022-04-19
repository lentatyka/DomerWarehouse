package com.lentatyka.domerwarehouse.presentation.login.viewmodel

import android.text.TextUtils
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.lifecycle.*
import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.domain.login.usecase.LoginUseCase
import com.lentatyka.domerwarehouse.domain.login.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class LoginViewModel(
    private val loginUseCase: LoginUseCase<Nothing>
) : ViewModel() {

    private val _emailError = MutableLiveData<Boolean>()
    val emailError: LiveData<Boolean> get() = _emailError

    private val _passwordError = MutableLiveData<Boolean>()
    val passwordError: LiveData<Boolean> get() = _passwordError

    private val _response = MutableLiveData<Response<out UserInfo>>()
    val response: LiveData<Response<out UserInfo>> get() = _response

    protected fun isEmailValid(email: String): Boolean {
        return if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = true
            false
        } else
            true
    }

    protected fun isPasswordValid(password: String): Boolean {
        return if (TextUtils.isEmpty(password)) {
            _passwordError.value = true
            false
        } else
            true
    }

    protected fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(email, password).onEach {
                _response.postValue(it)
            }.collect()
        }
    }

    fun getEmailFocusChangeListener() = OnFocusChangeListener { v: View, hasFocus: Boolean ->
        if (hasFocus) {
            _emailError.value = false
        }
    }

    fun getPasswordFocusChangeListener() = OnFocusChangeListener { v: View, hasFocus: Boolean ->
        if (hasFocus) {
            _emailError.value = false
        }
    }
}