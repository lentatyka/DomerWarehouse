package com.lentatyka.domerwarehouse.di.login

import com.lentatyka.domerwarehouse.presentation.login.LoginActivity
import com.lentatyka.domerwarehouse.presentation.login.SignInFragment
import com.lentatyka.domerwarehouse.presentation.login.SignUpFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():LoginComponent
    }

    fun inject(activity: LoginActivity)
    fun inject(fragment: SignInFragment)
    fun inject(fragment: SignUpFragment)
}