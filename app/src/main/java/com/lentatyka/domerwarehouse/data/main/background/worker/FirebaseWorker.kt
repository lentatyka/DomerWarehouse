package com.lentatyka.domerwarehouse.data.main.background.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.lentatyka.domerwarehouse.data.main.background.FirebaseRepository
import com.lentatyka.domerwarehouse.data.main.background.RoomRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FirebaseWorker @AssistedInject constructor(
    @Assisted private val fooContext: Context,
    @Assisted private val params: WorkerParameters,
    private val fb: FirebaseRepository,
    private val productRepo: RoomRepository
) : CoroutineWorker(fooContext, params) {

    override suspend fun doWork(): Result {
        val list = fb.getProductList()
        productRepo(list)
        return Result.success()
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): FirebaseWorker
    }
}



