package com.lentatyka.domerwarehouse.data.main.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

const val TABLE_NAME = "product"
@Database(entities = [ProductData::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun appDao(): AppDao
}