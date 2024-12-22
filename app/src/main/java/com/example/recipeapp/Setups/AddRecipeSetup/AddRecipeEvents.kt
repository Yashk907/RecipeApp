package com.example.recipeapp.Setups.AddRecipeSetup

import android.content.Context
import android.net.Uri

sealed interface AddRecipeEvents {
    data class setTitle(val Title : String) : AddRecipeEvents
    data class setDuration(val Time : String) : AddRecipeEvents
    data class setCategory(val category : String) : AddRecipeEvents
    data class addImage(val image : Uri,
        val context: Context) : AddRecipeEvents
    data class addIngredientList(val list : List<Pair<String, String>>) : AddRecipeEvents
    data class addDirectionsList(val list :  List<String>) : AddRecipeEvents
    data class CreateRecipe(val Recipe : RecipeState) : AddRecipeEvents
}