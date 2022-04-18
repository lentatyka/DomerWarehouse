package com.lentatyka.domerwarehouse

import android.app.Application
import com.lentatyka.domerwarehouse.di.AppComponent
import com.lentatyka.domerwarehouse.di.DaggerAppComponent

open class DomerApp:Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create()
    }

}