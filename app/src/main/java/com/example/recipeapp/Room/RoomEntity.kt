package com.example.recipeapp.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecipeTable")
data class RecipeEntity(
    val title : String="",
    val Duration : String="",
    val Category : String ="",
    val Ingredients : List<Pair<String, String>> = listOf(),
    val Directions : List<String> = listOf(),
    val image : ByteArray?=null,
    @PrimaryKey(autoGenerate = true)
    val id  : Int =0
)