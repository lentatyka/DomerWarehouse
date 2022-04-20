package com.lentatyka.domerwarehouse.data.main.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data
class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val color: String?,
    val size: String?,
    val address: String?
)