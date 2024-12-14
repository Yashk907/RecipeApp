import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.logging.Filter

@Composable
fun Chiprow(modifier: Modifier = Modifier) {
    val scrollState=rememberScrollState()
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val items =listOf<String>("Favourite",
        "A-z",
        "Newest")


    Row(modifier= modifier.fillMaxWidth()
        .horizontalScroll(scrollState)
    , horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items.forEachIndexed { index, string ->
            FilterChips(string,
                selected =selectedIndex==index,
                onClick = {selectedIndex=index},
                modifier = Modifier)
        }
        }
    }

@Composable
fun FilterChips(text : String,
                selected : Boolean,
                onClick :()-> Unit,
                modifier: Modifier = Modifier) {
    FilterChip(selected = selected,
        onClick = onClick,
        label = {Text(text)},
        modifier
    )
}