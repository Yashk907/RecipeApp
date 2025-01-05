package com.example.recipeapp.Viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.Repository.RecipeRoomRepo
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Setups.HomeScreenSetup.HomeScreenActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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

    private val _ScreenState = MutableStateFlow(HomeScreenState())
    val ScreenState : StateFlow<HomeScreenState>
        get() {
            return _ScreenState
        }

    //Managing filters
    private val _CurrentFilter = mutableStateOf("other")
    val CurrentFilter : State<String>
        get() {
            return _CurrentFilter
        }

    fun onActionHomeScreen(events : HomeScreenActions){
        when(events) {

            is HomeScreenActions.ShowDeleteDialog -> {
                _ScreenState.update{
                    it->
                    it.copy(
                        openDeleteDialog = events.show
                    )
                }
            }

            is HomeScreenActions.AddToFavourite ->{
                viewModelScope.launch{
                    Repo.updateRecipeForFavourite(events.favourite,events.id)
                    ReloadList()
                }

        }

            is HomeScreenActions.setFilter -> {
                _CurrentFilter.value=events.Filter
                viewModelScope.launch{
                    ReloadList()
                }
            }

            HomeScreenActions.DeleteRecipe -> {
                viewModelScope.launch{
                    Repo.deleteRecipe(ScreenState.value.DeleteEntity)
                    Repo.getAllRecipes()
                    viewModelScope.launch(Dispatchers.IO){
                        deleteImage(ScreenState.value.DeleteEntity)
                    }
                }
            }
            is HomeScreenActions.setDeleteEntity -> {
                _ScreenState.update{
                    it.copy(
                        DeleteEntity = events.state
                    )
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



   suspend private fun ReloadList(){
        when(CurrentFilter.value){
            "All"->Repo.getAllRecipes()
            "A-Z"->Repo.getRecipesbyAlphabet()
            "Favourite"->Repo.getFavouriteRecipes()
            else -> Repo.getAllRecipes()
        }
    }

}

data class HomeScreenState(
    val openDeleteDialog : Boolean =false,
    val DeleteEntity : RecipeEntity = RecipeEntity()
)