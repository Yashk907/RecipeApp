package com.example.recipeapp

import CustomBottomBar
import CustomTopAppBar
import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.Navigation.Navigation
import com.example.recipeapp.Setups.BottomNavigationBAr.BottomNavigationEvents
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.AddRecipeScreen
import com.example.recipeapp.Viewmodels.BottomNavigationBarViewmodel

import com.example.recipeapp.ui.theme.RecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeAppTheme {
                val NavController =  rememberNavController()
                val bottomNavigationBarViewmodel : BottomNavigationBarViewmodel = hiltViewModel()
                Scaffold(topBar = {CustomTopAppBar()},
                    bottomBar = {CustomBottomBar(bottomNavigationBarViewmodel,
                        navController = NavController)}) {
                    padding->
                    Box(modifier = Modifier.fillMaxSize()
                        .padding(padding)) {
                        Navigation(bottomNavigationBarViewmodel,
                            navController = NavController)
                    }
                }
            }
        }

    }
}

