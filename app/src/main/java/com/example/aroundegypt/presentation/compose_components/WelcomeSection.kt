package com.example.aroundegypt.presentation.compose_components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Created by AsmaaHassan on 17,December,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
@Composable
fun WelcomeSection() {
    Column {
        Text(
            text = "Welcome!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Now you can explore any experience in 360 degrees and get all the details about it all in one place.",
            color = Color.Black,
            fontSize = 14.sp
        )
    }
}
