package com.lentatyka.domerwarehouse.di.main

import android.content.Context
import androidx.room.Room
import com.lentatyka.domerwarehouse.data.main.room.AppDao
import com.lentatyka.domerwarehouse.data.main.room.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @ActivityScope
    @Provides
    fun provideRoomDao(applicationContext: Context): AppDao {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "product-database"
        )
            .build()
            .appDao()
    }
}