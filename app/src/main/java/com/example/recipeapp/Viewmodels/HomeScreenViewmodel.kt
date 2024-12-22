package com.example.recipeapp.Viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.Repository.RecipeRoomRepo
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Setups.HomeScreenSetup.HomeScreenActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewmodel @Inject constructor(private val Repo : RecipeRoomRepo) : ViewModel(){
    val RecipeList : StateFlow<List<RecipeEntity>>
        get() = Repo.RecipeList

    init {
        viewModelScope.launch{
            Repo.getAllRecipes()
            Log.d("yashtest2",RecipeList.value.toString())
        }
    }

    fun onActionHomeScreen(events : HomeScreenActions){
        when(events) {
            is HomeScreenActions.DeleteRecipe -> {
                viewModelScope.launch{
                    Repo.deleteRecipe(events.Recipe)
                    Repo.getAllRecipes()
                    viewModelScope.launch(Dispatchers.IO){
                        deleteImage(events.Recipe)
                    }
                }

            }
        }

    }

    private suspend fun deleteImage(Recipe : RecipeEntity){
        val file = File(Recipe.image.toString())
        if(file.exists()){
            file.delete()
        }
    }

}