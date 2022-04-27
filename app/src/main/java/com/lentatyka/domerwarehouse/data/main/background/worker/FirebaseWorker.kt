package com.lentatyka.domerwarehouse.data.main.background.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.data.main.background.FirebaseRepository
import com.lentatyka.domerwarehouse.data.main.background.RoomRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FirebaseWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val fb: FirebaseRepository,
    private val productRepo: RoomRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val list = fb.getProductList()
        productRepo(list)
        makeStatusNotification(context.getString(R.string.data_loading, list.size), context)
        return Result.success()
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): FirebaseWorker
    }

    private fun makeStatusNotification(message: String, context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = "CHANNEL_NAME"
            val description = "CHANNEL_DESC"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("CHANNEL_ID", name, importance)
            channel.description = description

            // Add the channel
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

            notificationManager?.createNotificationChannel(channel)
        }

        // Create the notification
        val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("NOTIFICATION_TITLE")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        // Show the notification
        NotificationManagerCompat.from(context).notify(0, builder.build())
    }

}



