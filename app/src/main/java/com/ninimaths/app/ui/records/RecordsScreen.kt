package com.ninimaths.app.ui.records

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ninimaths.app.data.Stats
import com.ninimaths.app.data.loadStats
import com.ninimaths.app.ui.theme.TealPrimary

private data class OpInfo(val sym: String, val label: String)

private val OPS = listOf(
    "add"      to OpInfo("+", "足し算"),
    "subtract" to OpInfo("−", "引き算"),
    "multiply" to OpInfo("×", "掛け算"),
    "divide"   to OpInfo("÷", "割り算"),
    "mix"      to OpInfo("◎", "ミックス")
)

@Composable
fun RecordsScreen(onHome: () -> Unit) {
    val context = LocalContext.current
    val stats = remember { loadStats(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "記録",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1A1A1A),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {

                    SectionLabel("プレイデータ")
                    RecordRow(
                        icon = { Icon(Icons.Outlined.Check, null, tint = TealPrimary, modifier = Modifier.size(22.dp)) },
                        name = "解いた数",
                        value = "${stats.totalSolved}"
                    )
                    RecordRow(
                        icon = { Icon(Icons.Outlined.Person, null, tint = TealPrimary, modifier = Modifier.size(22.dp)) },
                        name = "プレイヤーレベル",
                        value = "${stats.level()}"
                    )
                    RecordRow(
                        icon = { Icon(Icons.Outlined.Favorite, null, tint = TealPrimary, modifier = Modifier.size(22.dp)) },
                        name = "ポイント",
                        value = "${stats.points}"
                    )

                    SectionLabel("ハイスコア")
                    OPS.forEach { (id, info) ->
                        RecordRow(
                            icon = { OpCircle(info.sym) },
                            name = info.label,
                            value = "${stats.highScores[id] ?: 0}"
                        )
                    }

                    SectionLabel("ベストタイム（1問あたりの時間）")
                    if (stats.bestTimes.isEmpty()) {
                        Text(
                            text = "まだ記録なし",
                            fontSize = 13.sp,
                            color = Color(0xFFBBBBBB),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                        )
                    } else {
                        stats.bestTimes.forEach { (key, secs) ->
                            val parts = key.split("_")
                            if (parts.size == 3) {
                                val fd = parts[0]; val sd = parts[1]; val opId = parts[2]
                                val op = OPS.find { it.first == opId }?.second
                                if (op != null) {
                                    RecordRow(
                                        icon = { OpCircle(op.sym) },
                                        name = "${fd}桁と${sd}桁の${op.label}",
                                        value = "${"%.2f".format(secs)}秒"
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Text(
                    text = "日毎の記録",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                )
            }
        }

        // Bottom nav
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.Bookmark, null, tint = TealPrimary)
            }
            IconButton(onClick = onHome) {
                Icon(Icons.Outlined.Home, null, tint = Color(0xFF888888))
            }
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.Menu, null, tint = Color(0xFF888888))
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color(0xFF999999),
        modifier = Modifier.padding(top = 20.dp, bottom = 8.dp, start = 4.dp)
    )
}

@Composable
private fun RecordRow(icon: @Composable () -> Unit, name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(28.dp), contentAlignment = Alignment.Center) { icon() }
        Spacer(modifier = Modifier.width(14.dp))
        Text(name, modifier = Modifier.weight(1f), fontSize = 15.sp, color = Color(0xFF1A1A1A))
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1A1A1A))
    }
    HorizontalDivider(color = Color(0xFFF5F5F5), thickness = 1.dp)
}

@Composable
private fun OpCircle(sym: String) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .border(1.5.dp, TealPrimary, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(sym, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TealPrimary)
    }
}
