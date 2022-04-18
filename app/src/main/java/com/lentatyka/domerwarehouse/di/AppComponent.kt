package com.lentatyka.domerwarehouse.di

import android.app.Application
import com.lentatyka.domerwarehouse.di.login.LoginComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, AppSubComponent::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create():AppComponent
    }

    fun loginComponent():LoginComponent.Factory
}