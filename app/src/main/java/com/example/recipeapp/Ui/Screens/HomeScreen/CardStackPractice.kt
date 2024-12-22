//package com.example.recipeapp.Ui.Screens.HomeScreen
//
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.foundation.gestures.detectDragGestures
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.unit.dp
//import com.example.recipeapp.datastoreTemp
//import kotlin.math.abs
//
////@Preview(showSystemUi = true)
////@Composable
////private fun Preview() {
////    Box(modifier = Modifier.fillMaxSize()) {
////        CardStack(list = listOf(1,2,3,4,5,6), modifier = Modifier.align(Alignment.Center))
////    }
////
////}
//
//@Composable
//fun CardStack(list : List<datastoreTemp>,
//              modifier: Modifier = Modifier) {
//   var isTopCard by remember { mutableStateOf(false) }
//    var topCardIndex by remember { mutableStateOf(1) }
//    //for first card
//    var firstoffset by remember { mutableStateOf(Offset(20f,20f))}
//    val firstxpos by animateFloatAsState(
//        targetValue = firstoffset.x,
//    )
//    val firstypos by animateFloatAsState(
//        targetValue = firstoffset.y,
//    )
////for second card
//    var secondoffset by remember { mutableStateOf(Offset(0f,0f)) }
//
//    var reload by remember { mutableStateOf(true) }
//    //when the list should be relaunch
//
//
//        // Relaunch the list when topCardIndex reaches the end
//
//        if(topCardIndex>=list.size){
//            topCardIndex = 1
//            firstoffset= Offset(20f, 20f)
//            secondoffset = Offset(0f, 0f)
//        }
//
//
//    list.forEachIndexed{ index, i ->
//        isTopCard=index==topCardIndex
//        if(topCardIndex==list.size-1) {
//            RecipeSmallCard(images = i.image,
//                modifier = modifier.padding(20.dp)
//                    .offset(x = firstxpos.dp, y = firstypos.dp)
//                    .pointerInput(isTopCard) {
//                        detectDragGestures { change, dragAmount ->
//                            change.consume()
//                            firstoffset += Offset(dragAmount.x, dragAmount.y)
//                            if (abs(firstoffset.x) > size.width / 2 || abs(firstoffset.y) > size.height / 2) {
//                                topCardIndex++
//                            }
//                        }
//                    })
//        }
//            if(index==topCardIndex){
//                RecipeSmallCard(images = i.image,
//                    modifier= modifier.padding(20.dp)
//                    .offset(x=firstxpos.dp,y=firstypos.dp)
//                    .pointerInput(isTopCard){
//                        detectDragGestures { change, dragAmount ->
//                            change.consume()
//                            firstoffset+= Offset(dragAmount.x,dragAmount.y)
//                            if(abs(firstoffset.x)>size.width/2.5 || abs(firstoffset.y)>size.height/2.5){
//                                topCardIndex++
//                                firstoffset= Offset(20f,20f)
//                            }
//                        }
//                    })
//            }
//            if(index==topCardIndex-1){
//                RecipeSmallCard(images = i.image,
//                    modifier= modifier.padding(20.dp)
//                    .offset(x=secondoffset.x.dp, y = secondoffset.y.dp))
//            }
//
////        if (topCardIndex>list.size){
////            Button(onClick = {reload=true},
////                modifier = modifier) {
////                Text(text = "Reload List")
////            }
////        }
//
//    }
//
//}
