package com.youssefmsaber.caffeine.screen.order_done

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.youssefmsaber.caffeine.model.CoffeeSize

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.OrderDoneScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    coffeeImageId: Int,
    coffeeSize: CoffeeSize,
    modifier: Modifier = Modifier
) {

}