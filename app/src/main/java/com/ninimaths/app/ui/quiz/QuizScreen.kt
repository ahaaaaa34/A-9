package com.ninimaths.app.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Fullscreen
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ninimaths.app.data.QuizSettings
import com.ninimaths.app.data.generateProblems
import com.ninimaths.app.ui.theme.TealPrimary
import kotlinx.coroutines.delay

@Composable
fun QuizScreen(
    settings: QuizSettings,
    onClose: () -> Unit
) {
    var retryKey by remember { mutableIntStateOf(0) }
    val problems = remember(retryKey) { generateProblems(settings) }
    var currentIndex by remember(retryKey) { mutableIntStateOf(0) }
    var inputText by remember(retryKey) { mutableStateOf("") }
    var answerState by remember(retryKey) { mutableStateOf<AnswerState>(AnswerState.Idle) }
    var correctCount by remember(retryKey) { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    if (showResult) {
        ResultScreen(
            total = settings.questionCount,
            correct = correctCount,
            onClose = onClose,
            onRetry = {
                showResult = false
                retryKey++
            }
        )
        return
    }

    val currentProblem = problems.getOrNull(currentIndex)

    LaunchedEffect(answerState) {
        if (answerState is AnswerState.Correct) {
            delay(600)
            if (currentIndex + 1 >= settings.questionCount) {
                showResult = true
            } else {
                currentIndex++
                inputText = ""
                answerState = AnswerState.Idle
            }
        } else if (answerState is AnswerState.Wrong) {
            delay(400)
            answerState = AnswerState.Idle
        }
    }

    fun onKeyPress(key: String) {
        if (answerState != AnswerState.Idle) return
        when (key) {
            "C" -> inputText = ""
            "BS" -> if (inputText.isNotEmpty()) inputText = inputText.dropLast(1)
            else -> {
                if (inputText.length < 7) {
                    inputText += key
                }
                val enteredNum = inputText.toIntOrNull()
                if (enteredNum != null && currentProblem != null) {
                    if (enteredNum == currentProblem.answer) {
                        answerState = AnswerState.Correct
                        correctCount++
                    }
                }
            }
        }
    }

    val inputBorderColor = when (answerState) {
        is AnswerState.Correct -> Color(0xFF4CAF50)
        is AnswerState.Wrong -> Color(0xFFF44336)
        else -> Color(0xFFCCCCCC)
    }
    val inputBgColor = when (answerState) {
        is AnswerState.Correct -> Color(0xFFE8F5E9)
        is AnswerState.Wrong -> Color(0xFFFFEBEE)
        else -> Color.White
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Close",
                    tint = Color(0xFF1A1A1A)
                )
            }
            Text(
                text = "${currentIndex + 1} / ${settings.questionCount}",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )
            Row {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardDoubleArrowUp,
                        contentDescription = "Scroll up",
                        tint = Color(0xFF1A1A1A)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.Fullscreen,
                        contentDescription = "Expand",
                        tint = Color(0xFF1A1A1A)
                    )
                }
            }
        }

        HorizontalDivider(color = Color(0xFFEEEEEE))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (currentProblem != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${currentProblem.displayA} ${currentProblem.displayOp} ${currentProblem.displayB}",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily.Default,
                        color = Color(0xFF1A1A1A)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        IconButton(
                            onClick = {
                                if (currentIndex > 0) {
                                    currentIndex--
                                    inputText = ""
                                    answerState = AnswerState.Idle
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ChevronLeft,
                                contentDescription = "Previous",
                                tint = Color(0xFF888888),
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .height(52.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(inputBgColor)
                                .border(1.5.dp, inputBorderColor, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = inputText,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF1A1A1A)
                            )
                        }
                    }
                }
            }
        }

        HorizontalDivider(color = Color(0xFFEEEEEE))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            val keys = listOf(
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9"),
                listOf("C", "0", "BS")
            )
            keys.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1.4f)
                            .background(Color(0xFFF5F5F5))
                    )
                    row.forEach { key ->
                        NumpadKey(
                            label = key,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1.4f),
                            onClick = { onKeyPress(key) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NumpadKey(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .border(0.5.dp, Color(0xFFEEEEEE))
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF1A1A1A)
        )
    }
}

@Composable
private fun ResultScreen(
    total: Int,
    correct: Int,
    onClose: () -> Unit,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "結果",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A1A)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "$correct / $total",
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            color = TealPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "正解",
            fontSize = 18.sp,
            color = Color(0xFF888888)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(
                onClick = onClose,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("ホーム", color = TealPrimary)
            }
            Button(
                onClick = onRetry,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
            ) {
                Text("もう一度", color = Color.White)
            }
        }
    }
}

private sealed class AnswerState {
    object Idle : AnswerState()
    object Correct : AnswerState()
    object Wrong : AnswerState()
}
