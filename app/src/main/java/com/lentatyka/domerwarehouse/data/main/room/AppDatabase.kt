package com.lentatyka.domerwarehouse.data.main.room

import androidx.room.Database
import androidx.room.RoomDatabase

const val TABLE_NAME = "product"
@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun appDao(): AppDao
}