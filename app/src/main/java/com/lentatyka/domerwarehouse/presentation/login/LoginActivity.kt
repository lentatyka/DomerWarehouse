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
        Intent(this, MainActivity::class.java).also {
            it.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
            )
            startActivity(it)
        }
        loginComponent = (application as DomerApp).appComponent.loginComponent().create()
        loginComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}