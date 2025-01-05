package com.example.recipeapp.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipeapp.Room.Converters.Converters

@Database(
entities =[RecipeEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RecipeDatabse  : RoomDatabase(){
abstract val dao : RecipeDao
}