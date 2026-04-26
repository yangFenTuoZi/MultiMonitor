package itosang.multimonitor.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
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
fun MemBlock(
    modifier: Modifier = Modifier,
    memTotalKb: Long,
    memAvailableKb: Long,
    swapTotalKb: Long,
    swapFreeKb: Long
) {
    val memProcess = 1.0f * memAvailableKb / memTotalKb
    val swapProcess = 1.0f * (swapTotalKb - swapFreeKb) / swapTotalKb

    val memProcessColor = if (memProcess >= 0.85) MaterialTheme.colorScheme.error
    else MaterialTheme.colorScheme.primary

    val swapProcessColor = if (swapProcess >= 0.85) MaterialTheme.colorScheme.error
    else MaterialTheme.colorScheme.primary

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(54.dp),
            progress = { memProcess },
            color = memProcessColor,
            strokeWidth = 6.dp,
        )
        Column(
            Modifier
                .weight(1F)
                .padding(start = 12.dp)
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                progress = { memProcess },
                color = memProcessColor,
                gapSize = 7.dp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "物理内存      ${memProcess * 100}%",
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                progress = { swapProcess },
                color = swapProcessColor
            )
            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "交换分区      ${swapProcess * 100}%",
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun MemBlockPreview() {
    MemBlock(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        memTotalKb = 16777216,
        memAvailableKb = 8388608,
        swapTotalKb = 33554432,
        swapFreeKb = 25165824
    )
}