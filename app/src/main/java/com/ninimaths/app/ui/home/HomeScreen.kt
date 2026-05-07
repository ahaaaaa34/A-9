package com.ninimaths.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ninimaths.app.data.QuizSettings
import com.ninimaths.app.ui.settings.SettingsSheet
import com.ninimaths.app.ui.theme.BackgroundGray
import com.ninimaths.app.ui.theme.TealPrimary
import com.ninimaths.app.ui.theme.TextGray

@Composable
fun HomeScreen(
    onStartQuiz: (QuizSettings) -> Unit,
    onRecords: () -> Unit
) {
    var showSettings by remember { mutableStateOf(false) }
    var quizSettings by remember { mutableStateOf(QuizSettings()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 88.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Mathix",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Records card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clickable { onRecords() },
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Schedule,
                        contentDescription = "記録",
                        modifier = Modifier.size(28.dp),
                        tint = Color(0xFF1A1A1A)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "記録",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF1A1A1A)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Start card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable { onStartQuiz(quizSettings) },
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(84.dp)
                            .border(3.dp, TealPrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowUpward,
                            contentDescription = "Start",
                            modifier = Modifier.size(44.dp),
                            tint = TealPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Start",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = TealPrimary
                    )
                }
            }
        }

        // Bottom nav
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onRecords() }) {
                Icon(
                    imageVector = Icons.Outlined.Bookmark,
                    contentDescription = "Records",
                    tint = TextGray
                )
            }

            // Nav pill — dynamic, opens settings
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
                    .clickable { showSettings = true }
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        "•".repeat(quizSettings.firstDigits),
                        color = TextGray,
                        fontSize = 14.sp,
                        letterSpacing = 2.sp
                    )
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFF1A1A1A), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = quizSettings.operation.symbol,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        "•".repeat(quizSettings.secondDigits),
                        color = TextGray,
                        fontSize = 14.sp,
                        letterSpacing = 2.sp
                    )
                }
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Menu",
                    tint = TextGray
                )
            }
        }
    }

    if (showSettings) {
        SettingsSheet(
            settings = quizSettings,
            onSettingsChange = { quizSettings = it },
            onDismiss = { showSettings = false },
            onStart = {
                showSettings = false
                onStartQuiz(quizSettings)
            }
        )
    }
}
