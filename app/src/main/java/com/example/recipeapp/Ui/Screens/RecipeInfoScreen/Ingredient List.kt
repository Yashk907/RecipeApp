package com.example.recipeapp.Ui.Screens.RecipeInfoScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Setups.AddRecipeSetup.RecipeState

//@Preview(showSystemUi = true)
//@Composable
//fun Preview(modifier: Modifier = Modifier) {
//    val list = listOf(Pair("Sugar","4cups"),
//        Pair("Butter","2Cups"))
//    Column (modifier= Modifier.fillMaxWidth()
//        .statusBarsPadding()){
//        Ingredients(list,
//            )
//    }
//}

@Composable
fun Ingredients(state: RecipeEntity,
                modifier: Modifier = Modifier) {
    val list = state.Ingredients
    list.forEachIndexed { index, pair ->
        Row (modifier=modifier.fillMaxWidth()){
            Text(text = pair.first,
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelMedium,
                modifier= Modifier.weight(1f))
            Text(text = pair.second,
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelMedium,
                modifier= Modifier.weight(0.7f)
                    )
        }
        HorizontalDivider(modifier)
    }

}