package com.example.recipeapp.Navigation

import HomeScreen
import android.R
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
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.AddRecipeScreen
import com.example.recipeapp.Ui.Screens.RecipeInfoScreen.RecipeInfoScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(navController: NavHostController,
               modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "${Screens.HOMESCREEN}",
        enterTransition ={fadeIn()},
        exitTransition ={fadeOut()},
        popEnterTransition ={fadeIn()},
        popExitTransition = {fadeOut()},
        ){
        composable(route = "${Screens.HOMESCREEN}",
            ){
            HomeScreen(onClickCard = {navController.navigate(route="${Screens.RECIPESCREEN}/$it")})

        }
        composable(route = "${Screens.CREATESCREEN}"){
            AddRecipeScreen()
        }
        composable(route = "${Screens.SOCIALSCREEN}",){
            Text("Under Development!!!")
        }
        composable(route = "${Screens.RECIPESCREEN}/{id}",
            arguments =listOf(
                navArgument("id"){
                    type= NavType.StringType
                }
            )){
            RecipeInfoScreen()
        }

    }

}