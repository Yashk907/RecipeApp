package com.example.recipeapp.Ui.Screens.AddRecipeScreen

import CustomBottomBar
import CustomTopAppBar
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.Navigation.Screens
import com.example.recipeapp.Setups.AddRecipeSetup.AddRecipeEvents
import com.example.recipeapp.Viewmodels.AddScreenViewmodel
import dagger.hilt.android.lifecycle.HiltViewModel

@Preview
@Composable
private fun Preview() {
//    AddRecipeScreen()
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun AddRecipeScreen(navController: NavController,
//    onEvent : (AddRecipeEvents)-> Unit,
    modifier: Modifier = Modifier) {
    val AddScreenViewmodel : AddScreenViewmodel = hiltViewModel()
    val onEvent =AddScreenViewmodel::onAddRecipeEvent
    val state = AddScreenViewmodel.state.collectAsState()
    BackHandler {
        navController.navigate(route = "${Screens.HOMESCREEN}")
    }
            RecipeForm(onEvent,
                state.value,
                modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp))
}