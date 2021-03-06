package com.lentatyka.domerwarehouse.di.login

import com.lentatyka.domerwarehouse.presentation.login.LoginActivity
import com.lentatyka.domerwarehouse.presentation.login.LoginViewModelFactory
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():LoginComponent
    }

    fun inject(activity: LoginActivity)

    fun viewModelFactory():LoginViewModelFactory
}