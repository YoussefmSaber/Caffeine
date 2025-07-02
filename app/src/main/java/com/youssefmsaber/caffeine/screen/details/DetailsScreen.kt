package com.youssefmsaber.caffeine.screen.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youssefmsaber.caffeine.R
import com.youssefmsaber.caffeine.coffeeCups
import com.youssefmsaber.caffeine.screen.composable.TopApp
import com.youssefmsaber.caffeine.ui.theme.Gray
import com.youssefmsaber.caffeine.ui.theme.Urbanist

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onNavigateBack: () -> Unit,
    coffeeId: Int,
    modifier: Modifier = Modifier
) {
    DetailsScreenContent(
        modifier = modifier,
        sharedTransitionScope = this,
        animatedVisibilityScope = animatedVisibilityScope,
        coffeeId = coffeeId,
        onNavigateBack = onNavigateBack,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun DetailsScreenContent(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    coffeeId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    with(sharedTransitionScope) {
        Column(
            modifier = modifier
                .background(Color.White)
                .windowInsetsPadding(WindowInsets.systemBars)
                .fillMaxSize()
        ) {
            TopApp(
                modifier = Modifier.padding(horizontal = 16.dp),
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
        }

    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
//    DetailsScreenContent(1)
}