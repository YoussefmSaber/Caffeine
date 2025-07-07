package com.youssefmsaber.caffeine.screen.order_done

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
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
import com.youssefmsaber.caffeine.R
import com.youssefmsaber.caffeine.data.coffeeCups
import com.youssefmsaber.caffeine.model.CoffeeSize
import com.youssefmsaber.caffeine.screen.composable.TopApp
import com.youssefmsaber.caffeine.screen.modifier.animateAlignmentAsState
import com.youssefmsaber.caffeine.screen.modifier.dropShadow
import com.youssefmsaber.caffeine.ui.theme.Brown
import com.youssefmsaber.caffeine.ui.theme.Gray
import com.youssefmsaber.caffeine.ui.theme.Urbanist

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.OrderDoneScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    coffeeId: Int,
    coffeeSize: CoffeeSize,
    onNavigateBack: () -> Unit,
    onNavigateSnack: () -> Unit
) {
    OrderDoneContent(
        coffeeId = coffeeId,
        coffeeSize = coffeeSize,
        onNavigateBack = onNavigateBack,
        onNavigateSnack = onNavigateSnack,
        sharedTransitionScope = this,
        animatedVisibilityScope = animatedVisibilityScope,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun OrderDoneContent(
    coffeeId: Int,
    coffeeSize: CoffeeSize,
    onNavigateBack: () -> Unit,
    onNavigateSnack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    var startAnimation by remember { mutableStateOf(false) }
    var takeAway by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }
    val coffeeScale = when (coffeeSize) {
        CoffeeSize.Large -> 1f
        CoffeeSize.Medium -> 0.815f
        CoffeeSize.Small -> 0.626f
    }
    val animateCoffeeCoverOffset by animateDpAsState(
        targetValue = if (!startAnimation) (-620).dp else {
            when (coffeeSize) {
                CoffeeSize.Large -> (-0).dp
                CoffeeSize.Medium -> 24.dp
                CoffeeSize.Small -> 72.dp
            }
        },
        animationSpec = tween(1000, easing = EaseInOut)
    )
    val animateClosePosition by animateDpAsState(
        targetValue = if (!startAnimation) (-100).dp else 0.dp,
        animationSpec = tween(1000, easing = EaseInOut)
    )
    val animateTextPosition by animateDpAsState(
        targetValue = if (!startAnimation) (-320).dp else 0.dp,
        animationSpec = tween(1000, easing = EaseInOut)
    )
    val animateTakeAwayPosition by animateAlignmentAsState(
        targetAlignment = if (takeAway) Alignment.CenterStart else Alignment.CenterEnd
    )
    val animateRotation by animateFloatAsState(
        targetValue = if (takeAway) -360f else 0f,
        animationSpec = tween(800, easing = EaseInOut)
    )
    val animateColor by animateColorAsState(
        targetValue = if (takeAway) Brown else Color(0xFFFFEEE7),
        animationSpec = tween(800, easing = EaseInCubic)
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
            modifier = Modifier.padding(16.dp),
            leading = {
                Icon(
                    modifier = Modifier
                        .offset(y = animateClosePosition)
                        .clip(CircleShape)
                        .clickable(onClick = onNavigateBack)
                        .size(48.dp)
                        .background(Gray)
                        .padding(12.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.cancel_01),
                    contentDescription = ""
                )
            }
        )
        Column(
            modifier = Modifier
                .padding(top = 32.dp)
                .offset(y = animateTextPosition),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                modifier = Modifier
                    .dropShadow(
                        CircleShape,
                        color = Color(0x80B94B23),
                        offsetX = 0.dp,
                        offsetY = 6.dp,
                        blur = 16.dp
                    )
                    .clip(CircleShape)
                    .clickable(onClick = onNavigateBack)
                    .size(56.dp)
                    .background(Brown)
                    .padding(12.dp),
                imageVector = ImageVector.vectorResource(R.drawable.tick_02),
                contentDescription = "",
                tint = Color(0xDEFFFFFF)
            )
            BasicText(
                text = "Your coffee is ready,\n" +
                        "Enjoy",
                style = TextStyle(
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    letterSpacing = 0.25.sp,
                    color = Color(0xDE1F1F1F),
                    textAlign = TextAlign.Center
                )
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(341.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "image/${coffeeId}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                    .size(300.dp)
                    .scale(coffeeScale),
                painter = painterResource(coffeeCups[coffeeId].emptyCup),
                contentDescription = "Coffee Cup image"
            )
            Image(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(270.dp)
                    .height(70.dp)
                    .scale(coffeeScale)
                    .offset(y = animateCoffeeCoverOffset),
                painter = painterResource(coffeeCups[coffeeId].cupCover),
                contentDescription = "Coffee Cup Cover",
            )
            Image(
                modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "logo/${coffeeId}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    .offset(y = 15.dp)
                    .scale(coffeeScale),
                imageVector = ImageVector.vectorResource(R.drawable.the_chance_coffe_big),
                contentDescription = "Big Coffee Image",
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .clip(CircleShape)
                        .height(41.dp)
                        .width(78.dp)
                        .background(animateColor)
                        .padding(1.dp)
                        .clickable(onClick = {
                            takeAway = !takeAway
                        })
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicText(
                            text = "OFF",
                            style = TextStyle(
                                color = Color(0x991F1F1F),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Urbanist,
                                letterSpacing = 0.25.sp
                            )
                        )
                        BasicText(
                            text = "ON",
                            style = TextStyle(
                                color = Color(0x99FFFFFF),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                letterSpacing = 0.25.sp
                            )
                        )
                    }
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .rotate(animateRotation)
                            .align(animateTakeAwayPosition),
                        painter = painterResource(coffeeCups[coffeeId].cupTop),
                        contentDescription = "Coffee Top"
                    )
                }
                BasicText(
                    "Take Away",
                    style = TextStyle(
                        color = Color(0xB31F1F1F),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Urbanist,
                        letterSpacing = 0.25.sp
                    )
                )
            }
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
                        .clickable(onClick = onNavigateSnack),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Take snack",
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

@Preview
@Composable
private fun OrderDonePreview(modifier: Modifier = Modifier) {
//    OrderDoneContent(
//        coffeeId = 1,
//        coffeeSize = CoffeeSize.Small,
//        onNavigateBack = { },
//        onNavigateSnack = {}
//    )
}