package com.example.recipeapp.Navigation

import HomeScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.AddRecipeScreen
import com.example.recipeapp.Ui.Screens.RecipeInfoScreen.RecipeInfoScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(navController: NavHostController,
               modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "${Screens.HOMESCREEN}",
        enterTransition = { slideInHorizontally(initialOffsetX = {8000} ) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-8000}) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {8000} ) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {-8000} ) },
        ){
        composable(route = "${Screens.HOMESCREEN}",
            ){
            HomeScreen()

        }
        composable(route = "${Screens.CREATESCREEN}"){
            AddRecipeScreen()
        }
        composable(route = "${Screens.SOCIALSCREEN}",){
            Text("Under Development!!!")
        }
        composable(route = "${Screens.RECIPESCREEN}",){
            RecipeInfoScreen()
        }

    }

}