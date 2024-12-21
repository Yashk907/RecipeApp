package com.example.recipeapp.Setups.HomeScreenSetup

import com.example.recipeapp.Room.RecipeEntity

sealed interface HomeScreenActions {
    data class DeleteRecipe( val Recipe : RecipeEntity) : HomeScreenActions
}