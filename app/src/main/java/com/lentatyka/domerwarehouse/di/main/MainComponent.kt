package com.lentatyka.domerwarehouse.di.main

import com.lentatyka.domerwarehouse.presentation.main.MainActivity
import com.lentatyka.domerwarehouse.presentation.main.MainViewModelFactory
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ProductModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():MainComponent
    }

    fun inject(activity: MainActivity)

    fun viewModelFactory():MainViewModelFactory
}