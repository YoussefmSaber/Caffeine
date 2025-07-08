package com.youssefmsaber.caffeine.screen.details.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youssefmsaber.caffeine.ui.theme.Urbanist

@Composable
fun CoffeeLevelItem(coffeeFocus: String, align: Alignment) {
    Box(
        modifier = Modifier.width(38.dp),
        contentAlignment = align
    ) {
        BasicText(
            coffeeFocus,
            style = TextStyle(
                color = Color(0x991F1F1F),
                fontSize = 10.sp,
                letterSpacing = 0.25.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
            )
        )
    }
}