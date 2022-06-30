package com.lentatyka.domerwarehouse.data.main.background.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject

class SampleWorkerFactory @Inject constructor(
    private val helloWorldWorkerFactory: FirebaseWorker.Factory,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker {
        return when (workerClassName) {
            FirebaseWorker::class.java.name ->
                helloWorldWorkerFactory.create(appContext, workerParameters)
            else -> throw IllegalArgumentException("Unknown worker")
        }
    }
}
