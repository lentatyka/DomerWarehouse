package com.lentatyka.domerwarehouse

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.lentatyka.domerwarehouse.data.main.background.worker.SampleWorkerFactory
import com.lentatyka.domerwarehouse.di.AppComponent
import com.lentatyka.domerwarehouse.di.DaggerAppComponent
import javax.inject.Inject

open class DomerApp:Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var sampleWorkerFactory: SampleWorkerFactory

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)
        super.onCreate()
        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(sampleWorkerFactory)
            .build()
        WorkManager.initialize(this, workManagerConfig)
    }

}