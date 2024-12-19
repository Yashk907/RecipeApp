package com.example.recipeapp.Ui.Screens.RecipeInfoScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R

@Preview
@Composable
private fun Preview() {
    ImageOfRecipe()
}

@Composable
fun ImageOfRecipe(modifier: Modifier = Modifier) {
    Card (shape = RoundedCornerShape(20.dp),
        modifier = modifier){
        Image(painter = painterResource(R.drawable.dish1),
            "Image of dish",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}