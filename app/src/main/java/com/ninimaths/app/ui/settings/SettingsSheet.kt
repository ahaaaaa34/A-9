package com.ninimaths.app.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ninimaths.app.data.*
import com.ninimaths.app.ui.theme.TealPrimary
import com.ninimaths.app.ui.theme.TextGray

private val settingsTabs = listOf("EXモード", "筆算表示", "キーパッド", "その他")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsSheet(
    settings: QuizSettings,
    onSettingsChange: (QuizSettings) -> Unit,
    onDismiss: () -> Unit,
    onStart: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFFF5F5F5),
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "暗算の設定",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                settingsTabs.forEachIndexed { index, tab ->
                    val isSelected = selectedTab == index
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(if (isSelected) Color(0xFFE0E0E0) else Color.White)
                            .clickable { selectedTab = index }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tab,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (index == 0 && !isSelected) TextGray else Color(0xFF1A1A1A)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Text(
                    text = settings.summaryText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TealPrimary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            SelectorRow(
                options = QUESTION_COUNT_OPTIONS,
                selectedValue = settings.questionCount,
                labelSuffix = "問",
                dotPatternIndex = { QUESTION_COUNT_OPTIONS.indexOf(it) },
                onSelect = { onSettingsChange(settings.copy(questionCount = it)) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            SelectorRow(
                options = DIGIT_OPTIONS,
                selectedValue = settings.firstDigits,
                labelSuffix = "桁",
                dotPatternIndex = { DIGIT_OPTIONS.indexOf(it) },
                onSelect = { onSettingsChange(settings.copy(firstDigits = it)) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            SelectorRow(
                options = DIGIT_OPTIONS,
                selectedValue = settings.secondDigits,
                labelSuffix = "桁",
                dotPatternIndex = { DIGIT_OPTIONS.indexOf(it) },
                onSelect = { onSettingsChange(settings.copy(secondDigits = it)) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OperationRow(
                selected = settings.operation,
                onSelect = { onSettingsChange(settings.copy(operation = it)) }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
            ) {
                Text(
                    text = "始める",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun <T> SelectorRow(
    options: List<T>,
    selectedValue: T,
    labelSuffix: String,
    dotPatternIndex: (T) -> Int,
    onSelect: (T) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            options.forEach { option ->
                val isSelected = option == selectedValue
                val patternIdx = dotPatternIndex(option)
                DotCell(
                    patternIndex = patternIdx,
                    label = if (isSelected) "$option$labelSuffix" else null,
                    isSelected = isSelected,
                    onClick = { onSelect(option) }
                )
            }
        }
    }
}

@Composable
private fun DotCell(
    patternIndex: Int,
    label: String?,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) TealPrimary else Color.Transparent
    val dotColor = if (isSelected) Color.White else Color(0xFF888888)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .widthIn(min = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DotPatternCanvas(patternIndex = patternIndex, dotColor = dotColor)
        if (label != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                color = dotColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun DotPatternCanvas(patternIndex: Int, dotColor: Color) {
    val size = 28.dp
    androidx.compose.foundation.Canvas(
        modifier = Modifier.size(size)
    ) {
        val r = 3.5.dp.toPx()
        val cx = this.size.width / 2f
        val cy = this.size.height / 2f
        val sp = 7.dp.toPx()

        val dots: List<Offset> = when (patternIndex) {
            0 -> listOf(Offset(cx, cy))
            1 -> listOf(Offset(cx - sp / 2, cy), Offset(cx + sp / 2, cy))
            2 -> listOf(
                Offset(cx - sp, cy), Offset(cx, cy), Offset(cx + sp, cy)
            )
            3 -> listOf(
                Offset(cx - sp / 2, cy - sp / 2), Offset(cx + sp / 2, cy - sp / 2),
                Offset(cx - sp / 2, cy + sp / 2), Offset(cx + sp / 2, cy + sp / 2)
            )
            else -> listOf(
                Offset(cx - sp, cy - sp), Offset(cx + sp, cy - sp),
                Offset(cx, cy),
                Offset(cx - sp, cy + sp), Offset(cx + sp, cy + sp)
            )
        }

        dots.forEach { offset ->
            drawCircle(
                color = dotColor,
                radius = r,
                center = offset,
                style = Fill
            )
        }
    }
}

@Composable
private fun OperationRow(
    selected: Operation,
    onSelect: (Operation) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Operation.entries.forEach { op ->
                val isSelected = op == selected
                OperationCell(
                    operation = op,
                    isSelected = isSelected,
                    onClick = { onSelect(op) }
                )
            }
        }
    }
}

@Composable
private fun OperationCell(
    operation: Operation,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) TealPrimary else Color.Transparent
    val contentColor = if (isSelected) Color.White else Color(0xFF1A1A1A)
    val borderColor = if (isSelected) TealPrimary else Color(0xFFCCCCCC)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .widthIn(min = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .border(1.5.dp, borderColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = operation.symbol,
                fontSize = if (operation == Operation.MIX) 14.sp else 18.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
        }
        if (isSelected) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = operation.label,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
