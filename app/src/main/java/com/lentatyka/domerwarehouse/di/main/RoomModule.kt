package com.lentatyka.domerwarehouse.di.main

import android.content.Context
import androidx.room.Room
import com.lentatyka.domerwarehouse.data.main.room.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun provideRoomDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "product-database"
        ).build()
    }
}