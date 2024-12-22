package com.example.recipeapp.Ui.Screens.RecipeInfoScreen

import CustomBottomBar
import CustomTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Viewmodels.RecipeScreenViewmodel
import com.example.recipeapp.ui.theme.RecipeAppTheme
import java.nio.file.WatchEvent
//
//@Preview
//@Composable
//private fun Preview() {
//    RecipeAppTheme {
//        Scaffold (bottomBar = {
//            CustomBottomBar()
//        },
//            topBar = {//for testing original will be different
//                CustomTopAppBar()
//            }){
//                padding->
//            RecipeInfoScreen(modifier = Modifier.padding(padding))
//        }
//    }
//
//}
//later adding stepwise recipe card
@Composable
fun RecipeInfoScreen(modifier: Modifier = Modifier) {
    val viewmodel : RecipeScreenViewmodel= hiltViewModel()
    val state = viewmodel.state.value
    val ScreenInfoState = viewmodel.RecipeInfoScreenstate.collectAsState()
    val scrollState = rememberScrollState()
    Column(modifier=modifier.fillMaxWidth()
        .verticalScroll(scrollState)) {
        TitleOfRecipe(state,modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp))
        ImageOfRecipe(state,modifier= Modifier.padding(horizontal = 25.dp))
        CategoryCard(viewmodel,modifier= Modifier.padding(horizontal = 70.dp, vertical = 12.dp))
        if(ScreenInfoState.value.IngredientScreen){
            Ingredients(state,modifier= Modifier.align(Alignment.CenterHorizontally)
                .padding(horizontal = 50.dp, vertical = 8.dp))
        }else{
            Directions(state,modifier= Modifier.fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 12.dp))
        }


    }
}