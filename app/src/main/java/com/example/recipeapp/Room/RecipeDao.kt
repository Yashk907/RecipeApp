package com.example.recipeapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao{
    @Upsert
    suspend fun upsertRecipes(recipe : RecipeEntity)

    @Delete
    suspend fun deleteRecipes(recipe: RecipeEntity)

    @Query("SELECT * FROM RecipeTable")
    suspend fun getAllRecipes() : List<RecipeEntity>

    @Query("SELECT * FROM RecipeTable ORDER BY title ASC")
    suspend fun getRecipesByAlphabatically() : List<RecipeEntity>

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1")
    suspend fun getRecipesByFavourite() : List<RecipeEntity>

    @Query("SELECT * FROM RecipeTable WHERE id =:id")
    suspend fun getRecipeById(id : Int) : RecipeEntity

    @Query("UPDATE RecipeTable SET favourite= :favourite WHERE id=:id")
    suspend fun updateRecipeFavouriteOption(favourite : Boolean, id: Int)
}