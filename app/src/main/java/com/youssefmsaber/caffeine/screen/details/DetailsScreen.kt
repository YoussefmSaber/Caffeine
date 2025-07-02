package com.youssefmsaber.caffeine.screen.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.youssefmsaber.caffeine.R
import com.youssefmsaber.caffeine.coffeeCups
import com.youssefmsaber.caffeine.model.CoffeeLevel
import com.youssefmsaber.caffeine.model.CoffeeSize
import com.youssefmsaber.caffeine.screen.composable.TopApp
import com.youssefmsaber.caffeine.screen.details.composable.AnimatedBeanIcon
import com.youssefmsaber.caffeine.screen.details.composable.CoffeeLevelItem
import com.youssefmsaber.caffeine.screen.details.composable.SizeSwitchItem
import com.youssefmsaber.caffeine.screen.modifier.dropShadow
import com.youssefmsaber.caffeine.ui.theme.Gray
import com.youssefmsaber.caffeine.ui.theme.Urbanist

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onNavigateBack: () -> Unit,
    coffeeId: Int,
    onOrderFinish: (Int, CoffeeSize) -> Unit,
    modifier: Modifier = Modifier
) {
    DetailsScreenContent(
        modifier = modifier,
        sharedTransitionScope = this,
        animatedVisibilityScope = animatedVisibilityScope,
        coffeeId = coffeeId,
        onNavigateBack = onNavigateBack,
        onOrderFinish = onOrderFinish,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun DetailsScreenContent(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    coffeeId: Int,
    onNavigateBack: () -> Unit,
    onOrderFinish: (Int, CoffeeSize) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel()
) {
    val selectedSize by viewModel.selectedSize.collectAsState()
    val coffeeLevel by viewModel.coffeeLevel.collectAsState()
    val sizeAmount = viewModel.getAmount(selectedSize)
    val coffeeSizeAnimation by animateDpAsState(
        targetValue = when (selectedSize) {
            CoffeeSize.Large -> 300.dp
            CoffeeSize.Medium -> 244.dp
            CoffeeSize.Small -> 188.dp
        },
        animationSpec = tween(
            durationMillis = 600,
            easing = EaseIn
        )
    )
    with(sharedTransitionScope) {
    Column(
        modifier = modifier
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopApp(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            leading = {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = onNavigateBack)
                        .size(48.dp)
                        .background(Gray)
                        .padding(12.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_right_04),
                    contentDescription = ""
                )
            },
            label = {
                BasicText(
                        modifier = Modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "text/${coffeeId}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    text = coffeeCups[coffeeId].name,
                    style = TextStyle(
                        color = Color(0xDE1F1F1F),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        letterSpacing = 0.25.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
        ) {
            AnimatedContent(
                targetState = sizeAmount,
                transitionSpec = {
                    fadeIn(tween(600)) togetherWith fadeOut(tween(600))
                }
            ) { size ->
                BasicText(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 64.dp),
                    text = size,
                    style = TextStyle(
                        color = Color(0x99000000),
                        fontFamily = Urbanist,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.25.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "image/${coffeeId}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(coffeeSizeAnimation),
                    painter = painterResource(R.drawable.empty_cup),
                    contentDescription = "Coffee Cup image"
                )
            }
        }
        Row(
            modifier = Modifier
                .wrapContentWidth(unbounded = true)
                .background(Color(0xFFF5F5F5), CircleShape)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CoffeeSize.entries.forEach { size ->
                SizeSwitchItem(
                    size = size,
                    isSelected = size == selectedSize,
                    onClick = { viewModel.onSizeSelected(size) }
                )
            }
        }
        Row(
            modifier = Modifier
                .wrapContentWidth(unbounded = true)
                .padding(top = 16.dp)
                .background(Color(0xFFF5F5F5), CircleShape)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CoffeeLevel.entries.forEach { level ->
                AnimatedBeanIcon(
                    isSelected = coffeeLevel == level,
                    onClick = { viewModel.setBeanLevel(level) }
                )

            }
            repeat(3) { index ->
            }
        }
        Row(
            modifier = Modifier.padding(top = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(19.dp)
        ) {
            CoffeeLevelItem("Low", Alignment.CenterStart)
            CoffeeLevelItem("Medium", Alignment.Center)
            CoffeeLevelItem("High", Alignment.CenterEnd)
        }
        Spacer(Modifier.weight(1f))
        Box(
            Modifier
                .padding(bottom = 50.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier
//                        .sharedElement(
//                            sharedContentState = rememberSharedContentState(key = "button/continue"),
//                            animatedVisibilityScope = animatedVisibilityScope
//                        )
                    .dropShadow(
                        shape = CircleShape,
                        color = Color.Black.copy(0.24f),
                        offsetY = 6.dp,
                        offsetX = 0.dp,
                        blur = 12.dp
                    )
                    .clip(CircleShape)
                    .background(Color(0xFF1f1f1f))
                    .padding(vertical = 16.dp, horizontal = 32.dp)
                    .clickable(onClick = { onOrderFinish(coffeeId, selectedSize) }),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Continue",
                    style = TextStyle(
                        color = Color(0xDEFFFFFF),
                        fontSize = 16.sp,
                        fontFamily = Urbanist,
                        letterSpacing = 0.25.sp
                    )
                )
                Icon(
                    modifier = Modifier.rotate(180f),
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_right_04),
                    contentDescription = "Coffee Vector",
                    tint = Color(0xDEFFFFFF)
                )
            }
        }
        }
    }
}


@Preview
@Composable
private fun DetailsScreenPreview() {
//    DetailsScreenContent(1, onNavigateBack = {}, onOrderFinish = { _, _ ->
//
//    })
}