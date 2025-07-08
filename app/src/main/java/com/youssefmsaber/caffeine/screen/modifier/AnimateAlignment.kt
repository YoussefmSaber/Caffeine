package com.youssefmsaber.caffeine.screen.modifier

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment

@Composable
fun animateAlignmentAsState(
    targetAlignment: Alignment
): State<Alignment> {
    val targetBiasAlignment = when (targetAlignment) {
        Alignment.TopStart -> BiasAlignment(-1f, -1f)
        Alignment.TopCenter -> BiasAlignment(0f, -1f)
        Alignment.TopEnd -> BiasAlignment(1f, -1f)
        Alignment.CenterStart -> BiasAlignment(-1f, 0f)
        Alignment.Center -> BiasAlignment(0f, 0f)
        Alignment.CenterEnd -> BiasAlignment(1f, 0f)
        Alignment.BottomStart -> BiasAlignment(-1f, 1f)
        Alignment.BottomCenter -> BiasAlignment(0f, 1f)
        Alignment.BottomEnd -> BiasAlignment(1f, 1f)
        is BiasAlignment -> targetAlignment
        else -> BiasAlignment(0f, 0f)
    }


    val horizontalBias by animateFloatAsState(
        targetValue = targetBiasAlignment.horizontalBias,
        animationSpec = tween(800, easing = EaseInOut)
    )
    val verticalBias by animateFloatAsState(
        targetValue = targetBiasAlignment.verticalBias,
        animationSpec = tween(800, easing = EaseInOut)
    )

    return remember(horizontalBias, verticalBias) {
        derivedStateOf { BiasAlignment(horizontalBias, verticalBias) }
    }
}