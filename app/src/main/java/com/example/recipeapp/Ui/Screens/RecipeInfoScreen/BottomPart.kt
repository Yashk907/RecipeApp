package com.example.recipeapp.Ui.Screens.RecipeInfoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.ui.theme.RecipeAppTheme

//selecting Ingredient and Recipe card
@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    RecipeAppTheme {
        Scaffold (){padding->
            Box(modifier = Modifier.padding(padding)
                .fillMaxSize()){
                CategoryCard(modifier = Modifier.padding(horizontal = 50.dp))

            }
        }
    }

}

@Composable
fun CategoryCard(modifier: Modifier = Modifier) {
    Card(shape = RoundedCornerShape(50.dp),
        modifier = modifier.fillMaxWidth()) {
        var selectedItem by rememberSaveable { mutableStateOf(0) }
        val list =listOf(
            "Ingredients",
            "Directions"
        )
        Row (modifier= Modifier.padding(10.dp)){
            list.forEachIndexed { index, string ->
            Box (modifier=Modifier.weight(1f)
                .clip(RoundedCornerShape(50.dp))
                .background(color = if(selectedItem==index) Color.White else Color.Transparent)
                .clickable{
                    selectedItem=index
                }
                .padding(5.dp)){
                Text(text = string,
                    style= MaterialTheme.typography.labelMedium,
                    modifier= Modifier.align(Alignment.Center)
                        .padding(5.dp))
            }
        }
        }


    }
}