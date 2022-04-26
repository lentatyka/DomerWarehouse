package com.lentatyka.domerwarehouse.di.main

import com.lentatyka.domerwarehouse.presentation.main.MainActivity
import com.lentatyka.domerwarehouse.presentation.main.editor.AddressChangeFragment
import com.lentatyka.domerwarehouse.presentation.main.editor.AddressChangeViewModel
import com.lentatyka.domerwarehouse.presentation.main.product.ProductFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ProductModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ProductFragment)
    fun inject(fragment: AddressChangeFragment)


    @Subcomponent.Factory
    interface Factory{
        fun create():MainComponent
    }
}