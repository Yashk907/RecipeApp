
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.Ui.Screens.HomeScreen.RecipeSmallCard
import com.example.recipeapp.Viewmodels.HomeScreenViewmodel
import com.example.recipeapp.datastore
import com.example.recipeapp.datastoreTemp
import com.example.recipeapp.ui.theme.RecipeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
    (showSystemUi = true)
@Composable
private fun Preview() {
    val navController =rememberNavController()
    RecipeAppTheme {Scaffold(
        topBar = {
            CustomTopAppBar()
        },
        bottomBar = {
            CustomBottomBar(navController )
        }

    ) {padding->
        HomeScreen(modifier = Modifier.padding(padding))
    }  }


}


@Composable
fun HomeScreen(
               modifier: Modifier = Modifier) {
    val viewmodel : HomeScreenViewmodel = hiltViewModel()
    val list = viewmodel.RecipeList.collectAsState()
    LazyColumn(modifier=modifier) {
        item{
            TopComponentMainScreen(modifier= Modifier)
        }
        item{
            Chiprow(modifier= Modifier.padding(start = 22.dp, end = 12.dp))
        }
//        item{
////            RecipeSmallCard(modifier = Modifier.padding(horizontal = 22.dp, vertical = 10.dp))
//            Box(modifier= Modifier.fillMaxSize()) {
//                CardStack(list = datastore().list,
//                    modifier= Modifier.align(Alignment.Center)
//                    .padding(10.dp))
//            }

//        }
        items(list.value) { item ->//this is temporary datastore
            RecipeSmallCard(state = item,
                modifier= Modifier.padding(horizontal = 13.dp, vertical = 10.dp)

            )
        }
    }
    
}
