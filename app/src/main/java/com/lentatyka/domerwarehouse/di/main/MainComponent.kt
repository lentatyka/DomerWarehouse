package com.lentatyka.domerwarehouse.di.main

import com.lentatyka.domerwarehouse.presentation.main.MainActivity
import com.lentatyka.domerwarehouse.presentation.main.product.ProductFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ProductModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ProductFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():MainComponent
    }
}