package com.example.recipeapp.Setups.AddRecipeSetup

import android.graphics.Bitmap

data class RecipeState(
    val Title : String="",
    val Duration : String="",
    val Category : String="",
    val Image : ByteArray ?= null,
    val IngredientList : List<Pair<String, String>> = listOf(),
    val DirectionsList : List<String> = listOf()
)
