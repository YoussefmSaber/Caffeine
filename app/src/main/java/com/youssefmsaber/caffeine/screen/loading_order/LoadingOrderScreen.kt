package com.youssefmsaber.caffeine.screen.loading_order

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youssefmsaber.caffeine.R
import com.youssefmsaber.caffeine.coffeeCups
import com.youssefmsaber.caffeine.model.CoffeeSize
import com.youssefmsaber.caffeine.ui.theme.Urbanist

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LoadingOrderScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    coffeeId: Int,
    coffeeSize: CoffeeSize,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 96.dp)
                .fillMaxWidth()
                .height(340.dp)
        ) {
            BasicText(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 64.dp),
                text = "200ML",
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
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "image/${coffeeId}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(height = 244.dp, width = 199.dp),
                    painter = painterResource(coffeeCups[coffeeId].imageId),
                    contentDescription = "Coffee Cup image"
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = 30.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.the_chance_coffe_big),
                    contentDescription = "Big Coffee Image",
                )
            }
        }
    }
}