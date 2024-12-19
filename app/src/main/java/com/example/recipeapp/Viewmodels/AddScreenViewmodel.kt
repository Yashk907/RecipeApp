package com.example.recipeapp.Viewmodels

import androidx.compose.ui.graphics.evaluateCubic
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.Repository.RecipeRoomRepo
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Setups.AddRecipeSetup.AddRecipeEvents
import com.example.recipeapp.Setups.AddRecipeSetup.RecipeState
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.Image
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.Ingredients
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.title
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class AddScreenViewmodel @Inject constructor(private val RecipeRepo : RecipeRoomRepo) : ViewModel(){
    private val _state = MutableStateFlow(RecipeState())
    val state : StateFlow<RecipeState>
        get() =_state

    fun onAddRecipeEvent(events: AddRecipeEvents){
        when(events) {
            is AddRecipeEvents.addDirectionsList -> {
                _state.update{
                    it.copy(
                        DirectionsList = events.list
                    )
                }
            }
            is AddRecipeEvents.addImage -> {
                _state.update{
                    it.copy(
                        Image = events.image
                    )
                }
            }
            is AddRecipeEvents.addIngredientList -> {
                _state.update{
                    it.copy(IngredientList = events.list)
                }
            }
            is AddRecipeEvents.setCategory -> {
                _state.update{
                    it.copy(
                        Category = events.category
                    )
                }
            }
            is AddRecipeEvents.setDuration -> {

                _state.update{
                    it.copy(
                        Duration = events.Time
                    )
                }
            }
            is AddRecipeEvents.setTitle -> {
                _state.update{
                    it.copy(
                        Title = events.Title
                    )
                }
            }

            is AddRecipeEvents.CreateRecipe -> {
                val Recipe = RecipeEntity(
                    title=state.value.Title,
                    Duration=state.value.Duration,
                    Category = state.value.Category,
                    Ingredients=state.value.IngredientList,
                    Directions = state.value.DirectionsList,
                    image = state.value.Image
                )
                viewModelScope.launch{
                    RecipeRepo.createRecipe(Recipe)
                }

            }
        }
    }
}