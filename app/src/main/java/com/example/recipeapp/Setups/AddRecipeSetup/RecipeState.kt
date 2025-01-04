package com.example.recipeapp.Setups.AddRecipeSetup

import android.graphics.Bitmap
import android.net.Uri

data class RecipeState(
    val Title : String="",
    val Duration : String="",
    val DurationUnit : String="",
    val Category : String="",
    val ImageUri : String?= null,
    val IngredientList : List<Pair<String, String>> = listOf(Pair("","")),
    val DirectionsList : List<String> = listOf("")
)
