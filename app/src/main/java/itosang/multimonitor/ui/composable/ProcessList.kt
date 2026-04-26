package itosang.multimonitor.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProcessList(
    modifier: Modifier = Modifier,
    processSummaries: List<ProcessSummary>,
    onClick: ((ProcessSummary) -> Unit)? = null
) {
    LazyColumn(modifier = modifier) {
        items(processSummaries) { key ->
            ProcessItem(
                modifier = if (onClick != null) {
                    Modifier.clickable { onClick(key) }
                } else {
                    Modifier
                }.padding(4.dp),
                summary = key
            )
        }
    }
}

@Preview
@Composable
private fun ProcessListPreview() {
    MaterialTheme() {
        ProcessList(
            modifier = Modifier.fillMaxWidth(),
            processSummaries = listOf(
                ProcessSummary(1, "init", 0.1f),
                ProcessSummary(2, "lmkd", 0.1f),
                ProcessSummary(3, "logd", 0.1f),
                ProcessSummary(4, "system_server", 0.2155f),
            ),
            onClick = {}
        )
    }
}

data class ProcessSummary(
    val pid: Int,
    val name: String,
    val utilizationRate: Float,
    val appName: String? = null,
    val icon: ImageBitmap? = null,
)

@Composable
private fun ProcessItem(
    modifier: Modifier = Modifier,
    summary: ProcessSummary
) {
    val showName = summary.appName ?: summary.name
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val icon = summary.icon
        if (icon == null) {
            Icon(
                imageVector = Icons.Default.Memory,
                contentDescription = showName
            )
        } else {
            Icon(
                bitmap = icon,
                contentDescription = showName
            )
        }
        Text(
            showName,
            style = MaterialTheme.typography.titleSmall
        )
        Box(
            modifier = Modifier.weight(1F),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                "%.1f".format(summary.utilizationRate * 100) + "%",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}