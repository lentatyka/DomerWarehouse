package com.lentatyka.domerwarehouse.data.main.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data
class ProductData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "size") val size: String?,
    @ColumnInfo(name = "address") val address: String?
)