import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {

    CustomTopAppBar()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(title = {
        Text(text = "Recipe",
            style = MaterialTheme.typography.titleMedium)
    },
        navigationIcon = { IconButton(onClick = {})
        {Icon(imageVector = Icons.Default.Menu,contentDescription = "Menu Icon")  }
            },
        actions = {
            IconButton(onClick = {})
            {
                Icon(imageVector = Icons.AutoMirrored.Filled.Message,
                    contentDescription = "message icon")
            }
        }

        )
}