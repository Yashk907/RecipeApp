package com.example.recipeapp.Room.Converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    // Converters for List<Pair<String, String>>
    @TypeConverter
    fun fromIngredientsList(value: List<Pair<String, String>>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIngredientsList(value: String): List<Pair<String, String>> {
        val type = object : TypeToken<List<Pair<String, String>>>() {}.type
        return gson.fromJson(value, type)
    }

    // Converters for List<String>
    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}
