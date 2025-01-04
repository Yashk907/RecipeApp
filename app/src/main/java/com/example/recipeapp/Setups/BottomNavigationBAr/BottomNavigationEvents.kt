package com.example.recipeapp.Setups.BottomNavigationBAr

import androidx.navigation.NavController
import com.example.recipeapp.Viewmodels.HomeScreenState
import okhttp3.Route

sealed interface BottomNavigationEvents {
    data class Onclick(val ScreenName : String,
        val navController: NavController): BottomNavigationEvents
    data class setState(val route: String?) : BottomNavigationEvents
}