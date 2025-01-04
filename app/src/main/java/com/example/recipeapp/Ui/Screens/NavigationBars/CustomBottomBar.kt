import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.recipeapp.Navigation.Screens
import com.example.recipeapp.Setups.BottomNavigationBAr.BottomNavigationEvents
import com.example.recipeapp.Viewmodels.BottomNavigationBarViewmodel
import com.example.recipeapp.ui.theme.RecipeAppTheme

data class DrawerItem(
    val string: String,
    val ScreenName : String,
    val selectedicon: ImageVector,
    val unselectedicon : ImageVector
)
//@Preview(showSystemUi = true)
//@Composable
//private fun Preview() {
//    RecipeAppTheme {
//        Scaffold(bottomBar = {CustomBottomBar()})
//        { padding->
//            Box(modifier = Modifier.padding(padding))
//        }
//
//    }
//}
@Composable
fun CustomBottomBar(viewmodel: BottomNavigationBarViewmodel,
                    navController: NavController,
                    modifier: Modifier = Modifier) {
    val onClick = viewmodel::OnBottomBarActions
    val currentScreen = viewmodel.currentScreen.value
    val navigationItem=listOf(
        DrawerItem(
            string = "Recipes",
            ScreenName = "${Screens.HOMESCREEN}",
            selectedicon = Icons.Filled.Dining,
            unselectedicon = Icons.Outlined.Dining,
        ),
        DrawerItem(
            string = "Add",
            ScreenName = "${Screens.CREATESCREEN}",
            selectedicon = Icons.Filled.AddCircle,
            unselectedicon = Icons.Outlined.AddCircleOutline
        ),
        DrawerItem(
            string = "Social",
            ScreenName = "${Screens.SOCIALSCREEN}",
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
                selected = it.ScreenName==currentScreen,
                onClick = {
                   onClick(BottomNavigationEvents.Onclick(ScreenName = it.ScreenName,
                       navController = navController))
                },
                icon = {
                    if( it.ScreenName==currentScreen){
                        Icon(imageVector = it.selectedicon,
                            "")
                    }else Icon(imageVector = it.unselectedicon,
                        "")
                }
                , label = {
                    Text(text = it.string)
                }
            )
            Log.d("navtest1", (it.ScreenName==currentScreen).toString())
        }
    }
}