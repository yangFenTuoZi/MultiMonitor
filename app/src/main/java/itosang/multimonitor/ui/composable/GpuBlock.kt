package itosang.multimonitor.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GpuBlock(
    modifier: Modifier = Modifier,
    frequencyMhz: Long,
    utilizationRate: Float,
    gpuModel: String
) {
    val processColor = if (utilizationRate >= 0.85) MaterialTheme.colorScheme.error
    else MaterialTheme.colorScheme.primary

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(54.dp),
            progress = { utilizationRate },
            color = processColor,
            strokeWidth = 6.dp,
        )
        Column(
            Modifier
                .weight(1F)
                .padding(start = 12.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "$frequencyMhz MHz",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "负载: GP${utilizationRate * 100}%",
                fontSize = 11.sp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = gpuModel,
                fontSize = 9.sp,
                maxLines = 3
            )
        }
    }
}

@Preview
@Composable
private fun GpuBlockPreview() {
    GpuBlock(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        frequencyMhz = 114514,
        utilizationRate = 0.8f,
        gpuModel = "NVIDIA RTX 5090\nAMD RX580 8G\nARM Mali-G925"
    )
}