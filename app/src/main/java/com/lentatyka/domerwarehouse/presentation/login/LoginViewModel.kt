package com.lentatyka.domerwarehouse.presentation.login

import android.text.TextUtils
import android.view.View.OnFocusChangeListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.domain.login.LoginInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val interactor: LoginInteractor
) : ViewModel() {

    private val _emailError = MutableLiveData<Boolean>()
    val emailError: LiveData<Boolean> get() = _emailError

    private val _passwordError = MutableLiveData<Boolean>()
    val passwordError: LiveData<Boolean> get() = _passwordError

    private val _response = MutableLiveData<Response<FirebaseUser>>()
    val response: LiveData<Response<FirebaseUser>> get() = _response

    private fun isEmailValid(email: String): Boolean {
        return if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = true
            false
        } else
            true
    }

    private fun isPasswordValid(password: String): Boolean {
        return if (TextUtils.isEmpty(password)) {
            _passwordError.value = true
            false
        } else
            true
    }

    fun login(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            viewModelScope.launch(Dispatchers.IO) {
                interactor(email, password).onEach {
                    _response.postValue(it)
                }.collect()
            }
        }
    }

    fun getEmailFocusChangeListener() = OnFocusChangeListener { _, hasFocus: Boolean ->
        if (hasFocus) {
            _emailError.value = false
        }
    }

    fun getPasswordFocusChangeListener() = OnFocusChangeListener { _, hasFocus: Boolean ->
        if (hasFocus) {
            _passwordError.value = false
        }
    }
}