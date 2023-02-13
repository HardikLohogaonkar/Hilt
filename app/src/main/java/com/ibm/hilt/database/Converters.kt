package com.ibm.hilt.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromIntList(value: List<Int>): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun getIntList(value: String): List<Int> {
        return Gson().fromJson(value, object : TypeToken<List<Int>>() {}.type)
    }

//    @TypeConverter
//    fun fromArrayList(value: String):List<*>
}