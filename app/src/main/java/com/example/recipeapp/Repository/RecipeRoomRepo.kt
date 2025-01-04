package com.example.recipeapp.Repository

import com.example.recipeapp.Room.RecipeDao
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.RecipeForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RecipeRoomRepo @Inject constructor(private val dao : RecipeDao) {

    private val _RecipeList : MutableStateFlow<List<RecipeEntity>> = MutableStateFlow(listOf())
    val RecipeList : StateFlow<List<RecipeEntity>>
        get() =_RecipeList

    suspend fun getAllRecipes() {//here we are inserting database into _RecipeList
        val recipes =  dao.getAllRecipes()
        _RecipeList.value=recipes
    }
    suspend fun getRecipeByid(id : Int) : RecipeEntity=dao.getRecipeById(id)
    suspend fun getRecipesbyAlphabet(){
        val recipes=dao.getRecipesByAlphabatically()
        _RecipeList.value=recipes
    }
    suspend fun getFavouriteRecipes(){
        val recipes =dao.getRecipesByFavourite()
        _RecipeList.value=recipes
    }
    suspend fun createRecipe(Recipe : RecipeEntity) =dao.upsertRecipes(Recipe)
    suspend fun deleteRecipe(Recipe: RecipeEntity) =dao.deleteRecipes(Recipe)
    suspend fun updateRecipeForFavourite(favourite: Boolean ,id: Int)=dao.updateRecipeFavouriteOption(favourite,id)
}