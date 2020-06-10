package com.viveksharma.pokedex_new.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toString(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun toList(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toStringNextEvolution(value: List<NextEvolution>?): String? = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun toListNextEvolution(value: String?): List<NextEvolution>? {
        val gson = Gson()
        val type = object : TypeToken<List<NextEvolution>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toStringPrevEvolution(value: List<PrevEvolution>?): String? = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun toListPrevEvolution(value: String?): List<PrevEvolution>? {
        val gson = Gson()
        val type = object : TypeToken<List<PrevEvolution>>() {}.type
        return gson.fromJson(value, type)
    }
}