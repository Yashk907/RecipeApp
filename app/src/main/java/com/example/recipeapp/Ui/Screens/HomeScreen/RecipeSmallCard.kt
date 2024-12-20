package com.example.recipeapp.Ui.Screens.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.ui.theme.RecipeAppTheme
import java.nio.file.WatchEvent

//@Preview(showSystemUi = true)
//@Composable
//private fun Preview() {
//    RecipeAppTheme {
//        RecipeSmallCard(modifier = Modifier
//            .statusBarsPadding()
//            .padding(20.dp))
//    }
//
//}


@Composable
fun RecipeSmallCard(state : RecipeEntity ,
                    modifier: Modifier = Modifier) {
Card (modifier= modifier.fillMaxWidth()
    .clickable{

    },
    shape = RoundedCornerShape(20.dp)){
    Box(modifier= Modifier.fillMaxWidth()
    ){
//        RecipeImage( ,
//            modifier= Modifier.align(Alignment.Center))
        RecipeInfo(state,modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 20.dp)
            .fillMaxWidth(0.6f))
        BookMarkButton(modifier= Modifier
            .align(Alignment.TopEnd)
            .padding(top = 10.dp, end = 10.dp))
        FunctionRow(modifier= Modifier.align(Alignment.BottomEnd))
    }
}
}

@Composable
fun RecipeImage(Image : Int,
                modifier: Modifier = Modifier) {
    Image(painter = painterResource(Image),
        "Image of dish",
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(Color.Gray,
            blendMode = BlendMode.Multiply),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun RecipeInfo(state: RecipeEntity,
               modifier: Modifier = Modifier) {
    Column (modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)){
        Card(colors = CardDefaults.cardColors(contentColor =Color.White)) {
            Text(text = "BreakFast",
                color = Color.Black,
                style = MaterialTheme.typography.labelMedium,
                modifier= Modifier.padding(vertical = 5.dp,
                    horizontal = 5.dp))
        }
        Text(text = state.title,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
           )
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ebd4a)),
            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 12.dp)) {
            Text(text ="Explore",
                color = Color.White,
                style = MaterialTheme.typography.labelMedium)
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "forward Arrow icon",
                tint = Color.White,
                modifier = Modifier
                    .size(14.dp)
                    .padding(start = 3.dp)
            )
        }
    }
}

@Composable
fun BookMarkButton(modifier: Modifier = Modifier) {
    Box(modifier
        .clip(CircleShape)
        .background(color = Color.White),
        contentAlignment = Alignment.Center){
        Icon(imageVector = Icons.Default.BookmarkBorder,//writing logic of filled bookmark
            "BookMarkIcon",
            modifier= Modifier
                .padding(5.dp)
                .clickable {})
    }
}

@Composable
fun FunctionRow(modifier: Modifier = Modifier) {
    Box (modifier
        .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 0.dp,
            bottomStart = 0.dp, bottomEnd = 0.dp))
        .background(Color(1f,1f,1f,0.5f))
        ){
        Row (){
            IconButton(onClick = {},
                modifier= Modifier.padding(start = 2.dp)) {
                Icon(imageVector = Icons.Default.FavoriteBorder,//writing logic for filled like
                contentDescription = "Like button",
            ) }
            IconButton(onClick = {},
                modifier= Modifier.padding(end = 2.dp)) {
                Icon(imageVector = Icons.Default.Share,
                    contentDescription = "Share Icon",

                ) }
            }
    }
}