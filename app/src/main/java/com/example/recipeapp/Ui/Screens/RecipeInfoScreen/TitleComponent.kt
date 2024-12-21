package com.example.recipeapp.Ui.Screens.RecipeInfoScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Square
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.Room.RecipeEntity

//@Preview(showSystemUi = true)
//@Composable
//private fun Preview() {
//    TitleOfRecipe(modifier = Modifier
//        .statusBarsPadding()
//        .fillMaxWidth()
//        .padding(horizontal = 60.dp))
//}
@Composable
fun TitleOfRecipe(state : RecipeEntity,
                  modifier: Modifier = Modifier) {
    Column(modifier=modifier){
        Text(text = state.title,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            letterSpacing = 1.sp,
            style = MaterialTheme.typography.titleMedium,
            modifier= Modifier
                .align(Alignment.CenterHorizontally)
            )
        Text(text="by yash karande",
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier=Modifier.align(Alignment.CenterHorizontally))
        TimeAndCategory(state,modifier= Modifier.align(Alignment.CenterHorizontally)
            .padding(vertical = 5.dp))
    }
}

@Composable
fun TimeAndCategory(state: RecipeEntity,modifier: Modifier = Modifier) {
    Row(modifier) {
        DurationOFRecipe(state)
        VegOrNonVeg(state,modifier= Modifier.padding(start = 20.dp))
    }
}

@Composable
fun DurationOFRecipe(state: RecipeEntity,modifier: Modifier = Modifier) {
    Row (modifier){
        Icon(imageVector = Icons.Filled.AccessTimeFilled,
            contentDescription = "Timer Icon",
            tint = Color(0xFF1ebd4a))
        Text(text = state.Duration,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleSmall,
            modifier= Modifier.align(Alignment.CenterVertically)
                .padding(start = 2.dp))
    }
}
@Composable
fun VegOrNonVeg(state: RecipeEntity,
                modifier: Modifier = Modifier) {
    val IsNonVeg =state.Category=="Non-Veg"
    val tint =if(IsNonVeg) Color.Red else Color(0xFF1ebd4a)
    val text = if (IsNonVeg) "Non-Veg" else "Veg"
    Row (modifier){
        Icon(painter = painterResource(R.drawable.food_category),
            contentDescription = "veg/nonveg Icon",
            tint = tint)
        Text(text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleSmall,
            modifier= Modifier.align(Alignment.CenterVertically)
                .padding(start = 2.dp))
    }
}