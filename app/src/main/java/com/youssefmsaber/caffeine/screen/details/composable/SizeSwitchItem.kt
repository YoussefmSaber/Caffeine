package com.youssefmsaber.caffeine.screen.details.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youssefmsaber.caffeine.model.CoffeeSize
import com.youssefmsaber.caffeine.screen.modifier.dropShadow
import com.youssefmsaber.caffeine.ui.theme.Brown

@Composable
fun SizeSwitchItem(
    size: CoffeeSize,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn(tween(300, easing = EaseIn)),
            exit = fadeOut(tween(300, easing = EaseOut))
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .dropShadow(
                        shape = CircleShape,
                        color = Color(0x80B94B23),
                        offsetX = 0.dp,
                        offsetY = 6.dp
                    )
                    .clip(CircleShape)
                    .background(Brown, CircleShape)
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = isSelected,
                transitionSpec = {
                    fadeIn(tween(300, easing = EaseIn)) togetherWith fadeOut(
                        tween(300, easing = EaseOut)
                    )
                },
                label = "SizeSelection"
            ) { selected ->
                Text(
                    text = size.label,
                    color = if (selected) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}