package com.example.recipeapp.Ui.Screens.AddRecipeScreen

import CustomBottomBar
import CustomTopAppBar
import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
import android.text.Layout
import android.util.Log
import android.widget.RadioButton
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.TimePicker
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.example.recipeapp.Setups.AddRecipeSetup.AddRecipeEvents
import com.example.recipeapp.Setups.AddRecipeSetup.RecipeState
import com.example.recipeapp.ui.theme.RecipeAppTheme
import java.time.Duration

//@Preview(showSystemUi = true)
//@Composable
//private fun Preview() {
//    RecipeAppTheme {
//        Scaffold (topBar = { CustomTopAppBar() },
//            bottomBar = {CustomBottomBar()}){padding->
//            Box(modifier = Modifier
//                .padding(padding)
//                .fillMaxSize()){
//                RecipeForm(modifier = Modifier
//                    .statusBarsPadding()
//                    .padding(horizontal = 10.dp, vertical = 10.dp))
//            }
//
//        }
//
//    }
//
//}
@Composable
fun RecipeForm(onEvent : (AddRecipeEvents)-> Unit,
               state: RecipeState,
               modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column (
        modifier = modifier
            .verticalScroll(scrollState)){
        Column(modifier=Modifier.padding(5.dp))
        {//title
            TitleOfComponent("Name Of the Recipe")
            title(state,onEvent,)
            TitleOfComponent("Duration")
            Row {
                //Duration
                Duration(state,onEvent,)
                //radio buttonsWith Category
                Veg_NonVeg(state,onEvent,)

            }
            TitleOfComponent("Image")
            Image(state,onEvent,)
           TitleOfComponent("Ingredient")
            Ingredients(state,onEvent,)
            TitleOfComponent("Directions")
            Directions(state,onEvent,)

            CreateButton(state,
                onEvent,
                modifier= Modifier.align(Alignment.End))
        }

    }
}

@Composable
fun title(state: RecipeState,
          onEvent : (AddRecipeEvents)-> Unit,
          modifier: Modifier = Modifier) {
    OutlinedTextField(value = state.Title,
        onValueChange = {it->
            onEvent(AddRecipeEvents.setTitle(it))
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
fun Duration(state: RecipeState,
             onEvent : (AddRecipeEvents)-> Unit,
             modifier: Modifier = Modifier) {
    OutlinedTextField(value = state.Duration,
        onValueChange = {onEvent(AddRecipeEvents.setDuration(it))},
        label = {Text(text = "Time ",
            textAlign = TextAlign.Center)},
        modifier= Modifier
            .fillMaxWidth(0.3f)
            .padding(horizontal = 10.dp)
            .width(20.dp)
    )
}

@Composable
fun Veg_NonVeg(state: RecipeState,
               onEvent : (AddRecipeEvents)-> Unit,
               modifier: Modifier = Modifier) {
    val category =listOf("Veg","Non-Veg")
    var selectedcategory by rememberSaveable { mutableStateOf(3) }
    category.forEachIndexed { index, string ->
        Row (modifier= Modifier.selectable(selected = index==selectedcategory,
            onClick = {selectedcategory=index})){
            RadioButton(selected = index==selectedcategory,
                onClick = {
                    selectedcategory=index
                    onEvent(AddRecipeEvents.setCategory(category[index]))
                } ,
                modifier= Modifier.semantics { string }
            )
            Text(text = string,
                modifier= Modifier.align(Alignment.CenterVertically))
        }

    }
}

@Composable
fun Image(state: RecipeState,
          onEvent : (AddRecipeEvents)-> Unit,
          modifier: Modifier = Modifier) {
    //photo pick logic
// Get the context
    val context = LocalContext.current

    // PhotoPicker launcher
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            try {
                // Pass the URI as a string to the event handler
                onEvent(AddRecipeEvents.addImage(it,context))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
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
        if(state.ImageUri=="Wait"){
            LinearProgressIndicator(modifier= Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter))
        }else{
            if (state.ImageUri!=null){
                AsyncImage(state.ImageUri.toUri(),"",
                    contentScale = ContentScale.Crop)
            }
        }

        Column(modifier= Modifier.align(Alignment.Center)) { Text("Add Image",
            style = MaterialTheme.typography.labelMedium)
            IconButton(onClick = {
                //PhotoPicker Logic
                photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
                enabled =true
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
fun Ingredients(state: RecipeState,
                onEvent : (AddRecipeEvents)-> Unit,
                modifier: Modifier = Modifier) {
    // Using mutableStateListOf to ensure recomposition when the list changes
    val list = state.IngredientList.toMutableList()
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
                        onEvent(AddRecipeEvents.addIngredientList(list))
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(end = 2.dp)
                )

                // Weight Input
                OutlinedTextField(
                    value = pair.second,
                    label = { Text(text = "Quantity") },
                    onValueChange = { newValue ->
                        list[index] = pair.copy(second = newValue)
                        onEvent(AddRecipeEvents.addIngredientList(list))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                )
            }
        }


        // Add New Ingredient Button
        IconButton(onClick = {
            list.add(Pair("", ""))
            onEvent(AddRecipeEvents.addIngredientList(list))
        },
            enabled = if (state.IngredientList!=listOf(Pair("",""))) true else false) {
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

@SuppressLint("SuspiciousIndentation")
@Composable
fun Directions(state: RecipeState,
               onEvent : (AddRecipeEvents)-> Unit,
               modifier: Modifier = Modifier) {
    val DirectionList = state.DirectionsList.toMutableList()
        Column(modifier= modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
        ) {
            DirectionList.forEachIndexed { index, string ->
            OutlinedTextField(value =DirectionList[index],
                label = {Text(text = "Step ${index+1}")},
                onValueChange = {newvalue->
                    DirectionList[index]=newvalue
                    onEvent(AddRecipeEvents.addDirectionsList(DirectionList))
                },
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(end = 0.dp))


        }
            IconButton(onClick = {
                DirectionList.add("")
                onEvent(AddRecipeEvents.addDirectionsList(DirectionList))
            }, enabled = if(state.DirectionsList!=listOf(""))true else false) {
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
fun CreateButton(state: RecipeState,
                 onEvent : (AddRecipeEvents)-> Unit,
                 modifier: Modifier = Modifier) {
    val Enabled = remember { mutableStateOf(false) }
    if(state.ImageUri!="Wait" && state.ImageUri!=null &&
        state.Title!="" && state.DirectionsList!=listOf("")
        && state.IngredientList!=listOf(
            Pair("" ,""))) {
        Enabled.value=true
    } else{
        Enabled.value=false
    }
//adding logic when the button is activated and when not
    Button(onClick = {onEvent(AddRecipeEvents.CreateRecipe(state))},
        modifier,
        enabled =Enabled.value ,
        ) {
        Text(text = "Create",
            style = MaterialTheme.typography.titleSmall)

    }
}


