package com.youssefmsaber.caffeine.screen.details.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.youssefmsaber.caffeine.R
import com.youssefmsaber.caffeine.screen.modifier.dropShadow
import com.youssefmsaber.caffeine.ui.theme.Brown

@Composable
fun AnimatedBeanIcon(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val tint by animateColorAsState(
        targetValue = if (isSelected) Color(0xDEFFFFFF) else Color.Transparent,
        animationSpec = tween(durationMillis = 300, easing = EaseInOut)
    )
    val background by animateColorAsState(
        targetValue = if (isSelected) Brown else Color.Transparent,
        animationSpec = tween(durationMillis = 300, easing = EaseInOut)
    )

    Box(
        modifier = Modifier
            .size(40.dp)
            .then(
                if (isSelected) {
                    Modifier.dropShadow(
                        shape = CircleShape,
                        color = Color(0x80B94B23),
                        offsetX = 0.dp,
                        offsetY = 6.dp,
                    )
                } else {
                    Modifier
                }
            )
            .clip(CircleShape)
            .background(background)
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.coffee_beans),
            contentDescription = "Coffee Beans",
            tint = tint
        )
    }
}
