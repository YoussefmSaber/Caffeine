package com.youssefmsaber.caffeine.screen.chose_snack

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.youssefmsaber.caffeine.R
import com.youssefmsaber.caffeine.data.snacks
import com.youssefmsaber.caffeine.screen.composable.TopApp
import com.youssefmsaber.caffeine.screen.modifier.dropShadow
import com.youssefmsaber.caffeine.ui.theme.Black
import com.youssefmsaber.caffeine.ui.theme.Gray
import com.youssefmsaber.caffeine.ui.theme.Urbanist
import com.youssefmsaber.caffeine.ui.theme.White
import kotlin.math.absoluteValue
import kotlin.math.pow

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ChoseSnackScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onCloseClick: () -> Unit,
    onSnackChosen: (Int) -> Unit,
) {
    ChooseSnackContent(
        animatedVisibilityScope = animatedVisibilityScope,
        sharedTransitionScope = this,
        onCloseClick = onCloseClick,
        onSnackClick = onSnackChosen
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ChooseSnackContent(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onCloseClick: () -> Unit,
    onSnackClick: (Int) -> Unit,
) {
    val verticalPager = rememberPagerState(initialPage = 1) { snacks.size }
    Column(
        modifier = Modifier
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
                        .clip(CircleShape)
                        .clickable(onClick = onCloseClick)
                        .size(48.dp)
                        .background(Gray)
                        .padding(12.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.cancel_01),
                    contentDescription = ""
                )
            }
        )
        BasicText(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp),
            text = "Take your snack",
            style = TextStyle(
                color = Color(0xDE1F1F1F),
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                letterSpacing = 0.25.sp
            )
        )
        VerticalPager(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.Start),
            state = verticalPager,
            contentPadding = PaddingValues(top = 128.dp),
            pageSpacing = (-350).dp,
            beyondViewportPageCount = 5,
            flingBehavior = PagerDefaults.flingBehavior(
                state = verticalPager,
                snapPositionalThreshold = 0.2f,
                snapAnimationSpec = tween(
                    600, easing = EaseOut
                )
            )
        ) { snackId ->
            val pageOffsetFraction =
                (verticalPager.currentPage - snackId) + verticalPager.currentPageOffsetFraction

            val rotate = lerp(
                if (pageOffsetFraction < 0f) -8f else if (pageOffsetFraction > 0) -8f else 0f,
                0f,
                1f - pageOffsetFraction.coerceIn(-2f, 1f)
            )
            val offsetX = lerp(
                start = if (pageOffsetFraction < 0f) ((pageOffsetFraction.absoluteValue).pow(3) * -64f)
                else if (pageOffsetFraction > 0) ((pageOffsetFraction.absoluteValue).pow(3) * -64)
                else -24f,
                stop = -32f,
                fraction = 1f - pageOffsetFraction.absoluteValue.coerceIn(-1f, 1f)
            )
            val offsetY = lerp(
                start = ((pageOffsetFraction.absoluteValue).pow(3) * 64f),
                stop = -0f,
                fraction = 1f - pageOffsetFraction.absoluteValue.coerceIn(-1f, 1f)
            )
            val scale = lerp(
                start = 0.8f,
                stop = 1f,
                fraction = 1f - pageOffsetFraction.coerceIn(-1f, 1f)
            )

            SnackItem(
                modifier = Modifier
                    .scale(scale)
                    .rotate(rotate)
                    .offset(x = offsetX.dp, y = offsetY.dp),
                snackImageId = snacks[snackId],
                onClick = onSnackClick,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SnackItem(
    snackImageId: Int,
    onClick: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .dropShadow(
                shape = RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp),
                color = Black.copy(0.12f),
                offsetY = 4.dp,
                offsetX = 0.dp,
                blur = 20.dp,
                spread = 0.dp
            )
            .clip(RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp))
            .clickable { onClick(snackImageId) }
            .size(height = 274.dp, width = 280.dp)
            .background(White),
        contentAlignment = Alignment.Center
    ) {
        with(sharedTransitionScope) {
            Image(
                modifier = Modifier
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "snack/${snackImageId}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .height(149.dp)
                    .offset(x = 16.dp),
                painter = painterResource(snackImageId),
                contentDescription = "Snack Image"
            )

        }
    }
}

@Preview
@Composable
private fun ChooseSnackPreview() {
//    ChooseSnackContent() {}
}