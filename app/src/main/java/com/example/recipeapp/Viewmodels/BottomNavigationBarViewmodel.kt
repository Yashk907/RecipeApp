package com.example.recipeapp.Viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipeapp.Navigation.Screens
import com.example.recipeapp.Setups.BottomNavigationBAr.BottomNavigationEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationBarViewmodel @Inject constructor () : ViewModel(){
    private var _CurrentScreen = mutableStateOf("${Screens.HOMESCREEN}")
    val currentScreen : State<String>
        get() =_CurrentScreen

    fun OnBottomBarActions(events: BottomNavigationEvents){
        when(events){
            is BottomNavigationEvents.Onclick -> {
                if(events.ScreenName!=currentScreen.toString()){
                    _CurrentScreen.value=events.ScreenName
                    events.navController.navigate(currentScreen.value)
                }
            }

            is BottomNavigationEvents.setState -> {
                events.route?.let{
                    _CurrentScreen.value=it
                }
            }
        }
    }
}