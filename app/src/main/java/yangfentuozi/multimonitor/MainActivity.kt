package yangfentuozi.multimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import yangfentuozi.multimonitor.ui.screen.HomeScreen
import yangfentuozi.multimonitor.ui.theme.MultiMonitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiMonitorTheme {
                MultiMonitorApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun MultiMonitorApp() {
    val backStack = remember { mutableStateListOf(AppDestinations.HOME) }

    Column {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    AppDestinations.HOME -> NavEntry(key) {
                        HomeScreen()
                    }
                }
            }
        )
    }
}

enum class AppDestinations {
    HOME
}
