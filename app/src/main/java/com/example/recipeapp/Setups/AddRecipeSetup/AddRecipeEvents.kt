package com.example.recipeapp.Setups.AddRecipeSetup

sealed interface AddRecipeEvents {
    data class setTitle(val Title : String) : AddRecipeEvents
    data class setDuration(val Time : String) : AddRecipeEvents
    data class setCategory(val category : String) : AddRecipeEvents
    data class addImage(val image : String) : AddRecipeEvents
    data class addIngredientList(val list : List<Pair<String, String>>) : AddRecipeEvents
    data class addDirectionsList(val list :  List<String>) : AddRecipeEvents
    data class CreateRecipe(val Recipe : RecipeState) : AddRecipeEvents
}