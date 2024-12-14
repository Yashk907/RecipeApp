import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Dining
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.ui.theme.RecipeAppTheme

data class DrawerItem(
    val string: String,
    val selectedicon: ImageVector,
    val unselectedicon : ImageVector
)
@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    RecipeAppTheme {
        Scaffold(bottomBar = {CustomBottomBar()})
        { padding->
            Box(modifier = Modifier.padding(padding))
        }

    }
}
@Composable
fun CustomBottomBar(modifier: Modifier = Modifier) {
    val navigationItem=listOf(
        DrawerItem(
            string = "Recipes",
            selectedicon = Icons.Filled.Dining,
            unselectedicon = Icons.Outlined.Dining,
        ),
        DrawerItem(
            string = "Add",
            selectedicon = Icons.Filled.AddCircle,
            unselectedicon = Icons.Outlined.AddCircleOutline
        ),
        DrawerItem(
            string = "Social",
            selectedicon = Icons.Filled.Public,
            unselectedicon = Icons.Outlined.Public
        ),

        )
    var selectedDrawerIndex by rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar(modifier) {
        navigationItem.forEachIndexed { index, it ->
            NavigationBarItem(
                selected = selectedDrawerIndex==index,
                onClick = {
                    selectedDrawerIndex=index
                },
                icon = {
                    if(selectedDrawerIndex==index){
                        Icon(imageVector = it.selectedicon,
                            "")
                    }else Icon(imageVector = it.unselectedicon,
                        "")
                }
                , label = {
                    Text(text = it.string)
                }
            )
        }
    }
}