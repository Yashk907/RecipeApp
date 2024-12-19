package com.example.recipeapp.Ui.Screens.AddRecipeScreen

import CustomBottomBar
import CustomTopAppBar
import android.R
import android.media.Image
import android.text.Layout
import android.util.Log
import android.widget.RadioButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.ui.theme.RecipeAppTheme
import java.time.Duration

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    RecipeAppTheme {
        Scaffold (topBar = { CustomTopAppBar() },
            bottomBar = {CustomBottomBar()}){padding->
            Box(modifier = Modifier
                .padding(padding)
                .fillMaxSize()){
                RecipeForm(modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 10.dp, vertical = 10.dp))
            }

        }

    }

}
@Composable
fun RecipeForm(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Card (elevation = CardDefaults.elevatedCardElevation(10.dp),
        modifier = modifier
            .verticalScroll(scrollState)){
        Column(modifier=Modifier.padding(5.dp))
        {//title
            TitleOfComponent("Name Of the Recipe")
            title()
            TitleOfComponent("Duration")
            Row {
                //Duration
                Duration()
                //radio buttonsWith Category
                Veg_NonVeg()

            }
            TitleOfComponent("Image")
            Image()
           TitleOfComponent("Ingredient")
            Ingredients()
            TitleOfComponent("Directions")
            Directions()
            CreateButton(modifier= Modifier.align(Alignment.End))
        }

    }
}

@Composable
fun title(modifier: Modifier = Modifier) {
    var RecipeName by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(value = RecipeName,
        onValueChange = {it->
            RecipeName=it
        },
        label = {
            Text(text = "Title Of Recipe ")
        },
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    )
}

@Composable
fun Duration(modifier: Modifier = Modifier) {
    var Duration by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(value = Duration,
        onValueChange = {Duration=it},
        label = {Text(text = "Time",
            textAlign = TextAlign.Center)},
        modifier= Modifier
            .fillMaxWidth(0.3f)
            .padding(horizontal = 10.dp)
            .width(20.dp)
    )
}

@Composable
fun Veg_NonVeg(modifier: Modifier = Modifier) {
    val category =listOf("Veg","Nonveg")
    var selectedcategory by rememberSaveable { mutableStateOf(3) }
    category.forEachIndexed { index, string ->
        Row (modifier= Modifier.selectable(selected = index==selectedcategory,
            onClick = {selectedcategory=index})){
            RadioButton(selected = index==selectedcategory,
                onClick = {selectedcategory=index} ,
                modifier= Modifier.semantics { string }
            )
            Text(text = string,
                modifier= Modifier.align(Alignment.CenterVertically))
        }

    }
}

@Composable
fun Image(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp, vertical = 8.dp)
            .height(150.dp)
            .drawBehind {
                val strokeWidth = 4.dp.toPx()
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                drawRect(
                    color = Color.Black,
                    style = Stroke(width = strokeWidth, pathEffect = pathEffect)
                )
            }
            .background(Color.White) // Add a background if needed
    ){
        Column(modifier= Modifier.align(Alignment.Center)) { Text("Add Image",
            style = MaterialTheme.typography.labelMedium)
            IconButton(onClick = {
                //PhotoPicker Logic
            },
                ) {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp)){
                    Icon(imageVector = Icons.Default.Add,
                        "",
                        modifier= Modifier.padding(5.dp))
                }

            }

        }
    }
}

@Composable
fun Ingredients(modifier: Modifier = Modifier) {
    // Using mutableStateListOf to ensure recomposition when the list changes
    val list = remember { mutableStateListOf(Pair("", "")) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        list.forEachIndexed { index, pair ->
            Row {
                // Ingredient Name Input
                OutlinedTextField(
                    value = pair.first,
                    label = { Text(text = "Ingredient Name") },
                    onValueChange = { newValue ->
                        list[index] = pair.copy(first = newValue)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(end = 2.dp)
                )

                // Weight Input
                OutlinedTextField(
                    value = pair.second,
                    label = { Text(text = "Weight") },
                    onValueChange = { newValue ->
                        list[index] = pair.copy(second = newValue)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                )
            }
        }

        Log.d("yash", list.toString())

        // Add New Ingredient Button
        IconButton(onClick = {
            list.add(Pair("", ""))
        }) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ingredient Adding Button",
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun Directions(modifier: Modifier = Modifier) {
    var DirectionList = remember{ mutableStateListOf("") }
//var Directions by rememberSaveable { mutableStateOf("") }

        Column(modifier= modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
        ) {
            DirectionList.forEachIndexed { index, string ->
            OutlinedTextField(value =DirectionList[index],
                label = {Text(text = "Step ${index+1}")},
                onValueChange = {newvalue->
                    DirectionList[index]=newvalue
                },
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(end = 0.dp))


        }
            IconButton(onClick = {
                DirectionList.add("")
            }) {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp)){
                    Icon(imageVector = Icons.Default.Add,
                        "Direction Adding button",
                        modifier= Modifier.padding(5.dp))
                }

            }

    }

    }


@Composable
fun TitleOfComponent(text : String,
                     modifier: Modifier = Modifier) {
    Text(
        "${text} :",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(horizontal = 13.dp)
    )
}

@Composable
fun CreateButton(modifier: Modifier = Modifier) {
//adding logic when the button is activated and when not
    Button(onClick = {},
        modifier) {
        Text(text = "Create",
            style = MaterialTheme.typography.titleSmall)
    }
}