package com.example.recipeapp.Ui.Screens.RecipeInfoScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Setups.AddRecipeSetup.RecipeState

@Preview
@Composable
private fun Preview() {
}

@Composable
fun Directions(state: RecipeEntity,
               modifier: Modifier = Modifier) {
    val list = state.Directions
    Column(modifier) { list.forEachIndexed { index, string ->
        Text("${index+1}. $string",
            fontSize = 16.sp,
            style = MaterialTheme.typography.labelMedium)
    } }




}