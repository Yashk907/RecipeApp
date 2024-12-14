import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Preview(showBackground = true)
@Composable
private fun Preview2() {
    RecipeAppTheme {
        TopComponentMainScreen()
    }

}

@Composable
fun TopComponentMainScreen(modifier: Modifier = Modifier) {
    Card (colors = CardDefaults.cardColors(Color.Transparent),
        modifier = modifier){
        Box (modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp) // padding for the box
        ){
            TextComponent(modifier = Modifier.align(Alignment.TopStart))
            AvatarComponent(modifier= Modifier.align(Alignment.TopEnd))

        }
        SearchBarComponent(modifier= Modifier
            .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 15.dp))
    }
}

@Composable
fun TextComponent(modifier: Modifier = Modifier) {
    Column(modifier= modifier
        .fillMaxWidth(0.6f)) {
        Text(text = "Welcome yash",
            style = MaterialTheme.typography.titleSmall)
        Text(text = "Ready to Create Something Delicious!!",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun AvatarComponent(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .clip(CircleShape)
    ) {
        Image(painter = painterResource(R.drawable.example),
            "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(modifier: Modifier = Modifier) {
    SearchBar1(query = "",
        onQueryChange = {
        },
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {Icon(Icons.Default.Search,"search icon")},
        modifier = modifier) { }
    
}
