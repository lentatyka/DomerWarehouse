package com.lentatyka.domerwarehouse.di

import com.lentatyka.domerwarehouse.di.login.LoginComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        AppSubComponent::class,
        FirebaseModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory
}