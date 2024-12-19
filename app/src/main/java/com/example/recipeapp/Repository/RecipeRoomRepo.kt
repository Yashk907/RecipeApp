package com.example.recipeapp.Repository

import com.example.recipeapp.Room.RecipeDao
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.RecipeForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RecipeRoomRepo @Inject constructor(private val dao : RecipeDao) {

    private val _RecipeList : MutableStateFlow<RecipeEntity> = MutableStateFlow(RecipeEntity())
    val RecipeList : StateFlow<RecipeEntity>
        get() =_RecipeList

    suspend fun getAllRecipes() = dao.getAllRecipes()
    suspend fun getRecipesbyAlphabet() =dao.getRecipesByAlphabatically()
    suspend fun createRecipe(Recipe : RecipeEntity) =dao.upsertRecipes(Recipe)
    suspend fun deleteRecipe(Recipe: RecipeEntity) =dao.deleteRecipes(Recipe)
}