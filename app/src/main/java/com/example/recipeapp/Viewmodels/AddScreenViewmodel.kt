package com.example.recipeapp.Viewmodels

import android.R.attr.path
import android.content.Context
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Build
import android.util.Log
import androidx.annotation.Nullable
import androidx.compose.ui.graphics.evaluateCubic
import androidx.core.location.LocationRequestCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import coil3.Bitmap
import coil3.BitmapImage
import coil3.Uri
import com.example.recipeapp.Repository.RecipeRoomRepo
import com.example.recipeapp.Room.RecipeEntity
import com.example.recipeapp.Setups.AddRecipeSetup.AddRecipeEvents
import com.example.recipeapp.Setups.AddRecipeSetup.RecipeState
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.Image
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.Ingredients
import com.example.recipeapp.Ui.Screens.AddRecipeScreen.title
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.time.Duration

@HiltViewModel
class AddScreenViewmodel @Inject constructor(private val RecipeRepo : RecipeRoomRepo) : ViewModel(){
    private val _state = MutableStateFlow(RecipeState())
    val state : StateFlow<RecipeState>
        get() =_state
    fun onAddRecipeEvent(events: AddRecipeEvents){
        when(events) {
            is AddRecipeEvents.addDirectionsList -> {
                _state.update{
                    it.copy(
                        DirectionsList = events.list
                    )
                }
            }
            is AddRecipeEvents.addImage -> {
                _state.update{
                    it.copy(
                        ImageUri = "Wait"
                    )
                }
                viewModelScope.launch{
                    val path=SaveImageToInternal(events.context,events.image)
                    _state.update{
                        it.copy(
                            ImageUri = path
                        )
                    }
                }

            }
            is AddRecipeEvents.addIngredientList -> {
                _state.update{
                    it.copy(IngredientList = events.list)
                }
            }
            is AddRecipeEvents.setCategory -> {
                _state.update{
                    it.copy(
                        Category = events.category
                    )
                }
            }
            is AddRecipeEvents.setDuration -> {

                _state.update{
                    it.copy(
                        Duration = events.Time
                    )
                }
            }
            is AddRecipeEvents.setTitle -> {
                _state.update{
                    it.copy(
                        Title = events.Title
                    )
                }
            }

            is AddRecipeEvents.CreateRecipe -> {
                val recipe = RecipeEntity(
                    title=state.value.Title,
                    Duration=state.value.Duration,
                    Category = state.value.Category,
                    Ingredients=state.value.IngredientList,
                    Directions = state.value.DirectionsList,
                    image = state.value.ImageUri
                )
                Log.d("yashtest1",recipe.toString())
                viewModelScope.launch{
                    RecipeRepo.createRecipe(recipe)
                }
                _state.update{
                    it.copy(
                        Title = "",
                        Duration="",
                        Category = "",
                        ImageUri = null,
                        IngredientList = listOf(Pair("","")),
                        DirectionsList = listOf("")

                    )
                }

            }
        }
    }

    private suspend fun SaveImageToInternal(context: Context,Image : android.net.Uri) : String?{
    return withContext(Dispatchers.IO) {
        val fileName = "Image_${System.currentTimeMillis()}.jpg"
        val Imagefile = File(context.filesDir, fileName)
        val compressedImageBytes = CompressImage(context,Image,200*1024L)
        compressedImageBytes?.let { bytes ->
            FileOutputStream(Imagefile).use { output ->
                output.write(bytes)
            }
            Imagefile.absolutePath
        }?:run{
            null
        }
    }
    }

    private suspend fun CompressImage(context: Context,ImageUri: android.net.Uri ,compressThreshold: Long) : ByteArray? {
        return withContext(Dispatchers.IO){
            val mimeType=context.contentResolver.getType(ImageUri)
            val inputBytes = context
                .contentResolver
                .openInputStream(ImageUri)?.use { inputstream ->
                    inputstream.readBytes()
                }?:return@withContext null
            ensureActive()
            withContext(Dispatchers.Default){
                val bitmap = BitmapFactory.decodeByteArray(inputBytes,0,inputBytes.size)
                ensureActive()
                val compressFormat = when(mimeType){
                    "image/jpeg"-> android.graphics.Bitmap.CompressFormat.JPEG
                    "image/png"->android.graphics.Bitmap.CompressFormat.PNG
                    "image/webp"->if(Build.VERSION.SDK_INT>30){
                        android.graphics.Bitmap.CompressFormat.WEBP_LOSSLESS
                    }else{
                        android.graphics.Bitmap.CompressFormat.WEBP
                    }
                    else -> android.graphics.Bitmap.CompressFormat.JPEG
                }

                var outputBytes : ByteArray
                var Quality =90
                do {
                    ByteArrayOutputStream().use { output ->
                        bitmap.compress(compressFormat,Quality,output)
                        outputBytes=output.toByteArray()
                        Quality-=(0.1*Quality).roundToInt()
                    }
                }while (isActive &&
                    outputBytes.size >compressThreshold &&
                    Quality>5 &&
                    compressFormat!= android.graphics.Bitmap.CompressFormat.PNG
                )
                outputBytes
            }

        }
    }
}
