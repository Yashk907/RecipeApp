package com.example.recipeapp.Room

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecipeTable")
data class RecipeEntity(
    val title : String="",
    val Duration : String="",
    val Category : String ="",
    val Ingredients : List<Pair<String, String>> = listOf(),
    val Directions : List<String> = listOf(),
    val image : String?=null,
    val favourite : Boolean =false,
    @PrimaryKey(autoGenerate = true)
    val id  : Int =0
)