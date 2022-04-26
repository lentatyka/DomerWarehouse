package com.lentatyka.domerwarehouse.di.main

import com.lentatyka.domerwarehouse.presentation.main.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ProductModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():MainComponent
    }
}