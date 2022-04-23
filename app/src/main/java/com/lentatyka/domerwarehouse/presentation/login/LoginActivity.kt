package com.lentatyka.domerwarehouse.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lentatyka.domerwarehouse.DomerApp
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.di.login.LoginComponent
import com.lentatyka.domerwarehouse.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {

    lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        loginComponent = (application as DomerApp).appComponent.loginComponent().create()
        loginComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}