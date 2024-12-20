package com.example.recipeapp.Viewmodels

import androidx.lifecycle.ViewModel
import com.example.recipeapp.Repository.RecipeRoomRepo
import com.example.recipeapp.Room.RecipeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewmodel @Inject constructor(private val Repo : RecipeRoomRepo) : ViewModel(){
    val RecipeList : StateFlow<List<RecipeEntity>>
        get() = Repo.RecipeList
}