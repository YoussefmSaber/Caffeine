package com.youssefmsaber.caffeine.screen.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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

    val previousSize = remember { mutableStateOf(selectedSize) }
    val previousLevel = remember { mutableStateOf(coffeeLevel) }

    val sizeAmount = viewModel.getAmount(selectedSize)
    val coffeeScaleAnimation by animateFloatAsState(
        targetValue = when (selectedSize) {
            CoffeeSize.Large -> 1f
            CoffeeSize.Medium -> 0.815f
            CoffeeSize.Small -> 0.626f
        },
        animationSpec = tween(
            durationMillis = 600,
            easing = EaseIn
        )
    )
    val showBeans by remember(selectedSize, coffeeLevel) {
        mutableStateOf(
            viewModel.isCoffeeVisible(
                newSize = selectedSize,
                newLevel = coffeeLevel,
                oldSize = previousSize.value,
                oldLevel = previousLevel.value
            )
        )
    }

    LaunchedEffect(selectedSize, coffeeLevel) {
        previousSize.value = selectedSize
        previousLevel.value = coffeeLevel
    }

    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .background(Color.White)
                .windowInsetsPadding(WindowInsets.systemBars)
                .fillMaxSize(),
        ) {
            AnimatedCoffeeImage(
                modifier = Modifier.align(Alignment.TopCenter),
                isVisible = showBeans
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopApp(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 36.dp),
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
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Image(
                            modifier = Modifier
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(key = "image/${coffeeId}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .size(300.dp)
                                .scale(coffeeScaleAnimation),
                            painter = painterResource(coffeeCups[coffeeId].emptyCup),
                            contentDescription = "Coffee Cup image"
                        )
                        Image(
                            modifier = Modifier
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(key = "logo/${coffeeId}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .offset(y = 15.dp)
                                .scale(coffeeScaleAnimation),
                            imageVector = ImageVector.vectorResource(R.drawable.the_chance_coffe_big),
                            contentDescription = "Big Coffee Image",
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
                            onClick = {
                                viewModel.setBeanLevel(level)
                            }
                        )
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
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "button/continue"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
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
}

@Composable
fun AnimatedCoffeeImage(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = isVisible, label = "ImageTransition")

    val offsetY by transition.animateDp(
        transitionSpec = { tween(durationMillis = 800, easing = EaseInOut) },
        label = "OffsetY"
    ) { visible ->
        if (visible) 120.dp else (-300).dp // Slide from -300dp (above screen)
    }

    val scale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 800, easing = EaseInOut) },
        label = "Scale"
    ) { visible ->
        if (visible) 0.5f else 1.25f // Scale down from 1.5x to 1x
    }

    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 800, easing = EaseInOut) },
        label = "Alpha"
    ) { visible ->
        if (visible) 0f else 1f // Fade out when not visible
    }

    Image(
        painter = painterResource(R.drawable.coffe),
        contentDescription = "Coffee seeds",
        modifier = modifier
            .offset(y = offsetY)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
    )
}


@Preview
@Composable
private fun DetailsScreenPreview() {
//    DetailsScreenContent(1, onNavigateBack = {}, onOrderFinish = { _, _ ->
//
//    })
}