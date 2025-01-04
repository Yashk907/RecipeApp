package com.example.recipeapp.Navigation

import HomeScreen
import android.R
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.recipeapp.Setups.BottomNavigationBAr.BottomNavigationEvents
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.AddRecipeScreen
import com.example.recipeapp.Ui.Screens.RecipeInfoScreen.RecipeInfoScreen
import com.example.recipeapp.Viewmodels.BottomNavigationBarViewmodel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(bottomNavigationBarViewmodel: BottomNavigationBarViewmodel,
               navController: NavHostController,
               modifier: Modifier = Modifier) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    val context= LocalContext.current
    var backpressedOnce by remember{ mutableStateOf(false) }
    val exithandler = rememberUpdatedState { backpressedOnce = false }
    if(backpressedOnce==true){
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(2000)
            exithandler.value()
        }
    }

    // Update the BottomNavigationBarViewmodel whenever the route changes
    LaunchedEffect(currentRoute) {
        currentRoute?.let {
            bottomNavigationBarViewmodel.OnBottomBarActions(BottomNavigationEvents.setState(it))
        }
    }


    NavHost(navController = navController, startDestination = "${Screens.HOMESCREEN}",
        enterTransition ={fadeIn()},
        exitTransition ={fadeOut()},
        popEnterTransition ={fadeIn()},
        popExitTransition = {fadeOut()},
        ){
        composable(route = "${Screens.HOMESCREEN}",
            ){
            HomeScreen(
                onClickCard = {navController.navigate(route="${Screens.RECIPESCREEN}/$it")})
            BackHandler {
                if(backpressedOnce){
                    (context as? Activity)?.finish()
                }else{
                    backpressedOnce=true
                    Toast.makeText(context,"Press again to exit the app", Toast.LENGTH_SHORT).show()
                }
            }
        }
        composable(route = "${Screens.CREATESCREEN}"){
            AddRecipeScreen(navController)
        }
        composable(route = "${Screens.SOCIALSCREEN}",){
            BackHandler {
                navController.navigate(route = "${Screens.HOMESCREEN}")
            }
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