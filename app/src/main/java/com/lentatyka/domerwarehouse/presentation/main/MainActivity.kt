package com.lentatyka.domerwarehouse.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.lentatyka.domerwarehouse.DomerApp
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.data.main.background.worker.FirebaseWorker
import com.lentatyka.domerwarehouse.data.main.background.worker.SampleWorkerFactory
import com.lentatyka.domerwarehouse.di.main.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    @Inject
    lateinit var sampleWorkerFactory: SampleWorkerFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (application as DomerApp).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        WorkManager.getInstance(this).enqueue(
            OneTimeWorkRequestBuilder<FirebaseWorker>().build()
        )

    }
}