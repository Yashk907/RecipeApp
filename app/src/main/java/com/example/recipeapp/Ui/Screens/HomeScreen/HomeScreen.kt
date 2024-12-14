
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.Ui.Screens.HomeScreen.RecipeSmallCard
import com.example.recipeapp.ui.theme.RecipeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
    (showSystemUi = true)
@Composable
private fun Preview() {
    RecipeAppTheme {Scaffold(
        topBar = {
            TopAppBar(title = {Text(text = "Recipe",
                style = MaterialTheme.typography.titleMedium)})
        },
        bottomBar = {
            CustomBottomBar()
        }

    ) {padding->
        HomeScreen(modifier = Modifier.padding(padding))
    }  }


}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier=modifier) {
        item{
            TopComponentMainScreen(modifier= Modifier)
        }
        item{
            Chiprow(modifier= Modifier.padding(start = 22.dp, end = 12.dp))
        }
        item{
            RecipeSmallCard(modifier = Modifier.padding(horizontal = 22.dp, vertical = 10.dp))
        }
    }
    
}
