package com.example.recipeapp.Viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.Repository.RecipeRoomRepo
import com.example.recipeapp.Room.RecipeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecipeInfoState(
    val IngredientScreen : Boolean =true
)
@HiltViewModel
class RecipeScreenViewmodel @Inject constructor(private val repo: RecipeRoomRepo,
                                               private val savedStateHandle: SavedStateHandle ): ViewModel() {
    private val _state = mutableStateOf(RecipeEntity())
    val state : State<RecipeEntity>
        get() =_state


    init {
        val id= savedStateHandle.get<String>("id")!!.toInt()

        if(id!=null){
            getRecipeByid(id)
        }
    }

    fun getRecipeByid( id : Int){
        viewModelScope.launch{
           val recipe=repo.getRecipeByid(id)
            _state.value=recipe
        }
    }

    private val _RecipeScreenState = MutableStateFlow(RecipeInfoState())
    val RecipeInfoScreenstate : StateFlow<RecipeInfoState>
        get()= _RecipeScreenState

    fun IsIngredientScreen(IngredientScreen: Boolean){
        _RecipeScreenState.update{
            it.copy(IngredientScreen=IngredientScreen)
        }
    }
}