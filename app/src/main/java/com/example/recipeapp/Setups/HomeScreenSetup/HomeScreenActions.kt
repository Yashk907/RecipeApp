package com.example.recipeapp.Setups.HomeScreenSetup

import com.example.recipeapp.Room.RecipeEntity
import java.net.IDN

sealed interface HomeScreenActions {
    object DeleteRecipe : HomeScreenActions
    data class ShowDeleteDialog(val show : Boolean) : HomeScreenActions
    data class AddToFavourite(val favourite : Boolean, val id : Int) : HomeScreenActions
    data class setFilter(val Filter : String) : HomeScreenActions
    data class setDeleteEntity(val state: RecipeEntity) : HomeScreenActions
}