package com.lentatyka.domerwarehouse.data.main.room

import androidx.room.TypeConverter

class DataConverter {

    @TypeConverter
    fun fromCountryLangList(value: List<String>): String {
        return value.joinToString()
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<String> {
        return value.split(", ").map { it.trim() }
    }
}