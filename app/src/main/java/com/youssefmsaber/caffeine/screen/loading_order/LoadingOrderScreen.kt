package com.youssefmsaber.caffeine.screen.loading_order

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
import com.youssefmsaber.caffeine.ui.theme.Brown
import com.youssefmsaber.caffeine.ui.theme.Sniglet
import com.youssefmsaber.caffeine.ui.theme.Urbanist
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LoadingOrderScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    coffeeId: Int,
    coffeeSize: CoffeeSize,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    LoadingOrderContent(
        animatedVisibilityScope = animatedVisibilityScope,
        sharedTransitionScope = this,
        coffeeId = coffeeId,
        coffeeSize = coffeeSize,
        navigateToOrderDone = onNavigate,
        modifier = modifier
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun LoadingOrderContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    coffeeId: Int,
    coffeeSize: CoffeeSize,
    navigateToOrderDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Loading animation")

    val coffeeScale = when (coffeeSize) {
        CoffeeSize.Large -> 1f
        CoffeeSize.Medium -> 0.815f
        CoffeeSize.Small -> 0.626f
    }
    val loadingAnimation by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        infiniteRepeatable(
            animation = tween(durationMillis = 3250, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )
    LaunchedEffect(Unit) {
        delay(4500)
        navigateToOrderDone()
    }
    with(sharedTransitionScope) {
    Column(
        modifier = modifier
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 124.dp)
                .fillMaxWidth()
                .height(340.dp)
        ) {
            BasicText(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 64.dp),
                text = coffeeSize.amount,
                style = TextStyle(
                    color = Color(0x99000000),
                    fontFamily = Urbanist,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.25.sp,
                    textAlign = TextAlign.Center
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "image/${coffeeId}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .align(Alignment.Center)
                        .size(300.dp)
                        .scale(coffeeScale),
                    painter = painterResource(coffeeCups[coffeeId].emptyCup),
                    contentDescription = "Coffee Cup image"
                )
                Image(
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "logo/${coffeeId}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .align(Alignment.Center)
                        .offset(y = 15.dp)
                        .scale(coffeeScale),
                    imageVector = ImageVector.vectorResource(R.drawable.the_chance_coffe_big),
                    contentDescription = "Big Coffee Image",
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Column(
            modifier = Modifier.padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding(bottom = 37.dp)) {
                Image(
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth(),
                    painter = painterResource(R.drawable.loading),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Box(
                    Modifier
                        .align(Alignment.CenterEnd)
                        .background(Color.White)
                        .height(32.dp)
                        .fillMaxWidth(loadingAnimation)
                )
            }
            BasicText(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Almost Done",
                style = TextStyle(
                    color = Color(0xDE1F1F1F),
                    fontSize = 22.sp,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.25.sp
                )
            )
            BasicText(
                modifier = Modifier.padding(bottom = 12.dp),
                text = "Your coffee will be finish in",
                style = TextStyle(
                    color = Color(0x991F1F1F),
                    fontSize = 16.sp,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.25.sp
                )
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicText(
                    "CO",
                    style = TextStyle(
                        color = Brown,
                        fontSize = 32.sp,
                        fontFamily = Sniglet,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.25.sp
                    )
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.column),
                    contentDescription = "",
                    tint = Color(0x1F1F1F1F)
                )
                BasicText(
                    "FF",
                    style = TextStyle(
                        color = Brown,
                        fontSize = 32.sp,
                        fontFamily = Sniglet,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.25.sp
                    )
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.column),
                    contentDescription = "",
                    tint = Color(0x1F1F1F1F)
                )
                BasicText(
                    "EE",
                    style = TextStyle(
                        color = Brown,
                        fontSize = 32.sp,
                        fontFamily = Sniglet,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.25.sp
                    )
                )
            }
        }
    }
    }
}

@Preview
@Composable
private fun LoadingOrderPreview() {
//    LoadingOrderContent(
//        coffeeId = 1,
//        coffeeSize = CoffeeSize.Large,
//        {}
//    )
}