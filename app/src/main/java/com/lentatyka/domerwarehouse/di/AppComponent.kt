package com.lentatyka.domerwarehouse.di

import android.content.Context
import com.lentatyka.domerwarehouse.DomerApp
import com.lentatyka.domerwarehouse.di.login.LoginComponent
import com.lentatyka.domerwarehouse.di.main.MainComponent
import com.lentatyka.domerwarehouse.di.viewmodel.ViewModelFactoryModule
import com.lentatyka.domerwarehouse.presentation.main.editor.AddressChangeViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        AppSubComponent::class,
        FirebaseModule::class,
        BackgroundModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(app: DomerApp)

    fun loginComponent(): LoginComponent.Factory

    fun mainComponent(): MainComponent.Factory

}