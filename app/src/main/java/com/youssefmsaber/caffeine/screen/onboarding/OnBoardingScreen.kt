package com.youssefmsaber.caffeine.screen.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
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
import com.youssefmsaber.caffeine.screen.composable.TopApp
import com.youssefmsaber.caffeine.screen.modifier.dropShadow
import com.youssefmsaber.caffeine.ui.theme.Gray
import com.youssefmsaber.caffeine.ui.theme.Sniglet
import com.youssefmsaber.caffeine.ui.theme.Urbanist

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.OnBoardingScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navigateOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OnBoardingContent(
        modifier = modifier
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars),
        scope = this,
        animatedVisibilityScope = animatedVisibilityScope,
        navigateToHome = navigateOnClick
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun OnBoardingContent(
    modifier: Modifier = Modifier,
    scope: SharedTransitionScope,
    navigateToHome: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Ghost Animation")

    val ghostAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -36f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )
    val shadowAnimation by infiniteTransition.animateColor(
        initialValue = Color(0x24000000),
        targetValue = Color(0x14000000),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )
    val starAnimation by infiniteTransition.animateColor(
        initialValue = Color(0xDE1F1F1F),
        targetValue = Color(0x1F1F1F1F),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )
    with(scope) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopApp(
                Modifier.padding(bottom = 8.dp),
                leading = {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.pfp),
                        contentDescription = "Profile Image",
                    )
                },
                trailing = {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {}
                            .size(48.dp)
                            .background(Gray)
                            .padding(12.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.add_01),
                        contentDescription = ""
                    )
                }
            )
            Box(modifier = Modifier.padding(bottom = 42.dp)) {
                BasicText(
                    text = "Hocus\n" +
                            "Pocus\n" +
                            "I Need Coffee\n" +
                            "to Focus",
                    style = TextStyle(
                        color = Color(0xDE1F1F1F),
                        fontSize = 32.sp,
                        letterSpacing = 0.25.sp,
                        lineHeight = 50.sp,
                        fontFamily = Sniglet,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                )
                StarIcon(
                    color = starAnimation,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)
                        .offset(y = -24.dp)
                        .size(16.dp),
                )
                StarIcon(
                    color = starAnimation,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(16.dp),
                )
                StarIcon(
                    color = starAnimation,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 15.dp)
                        .size(16.dp)
                )
            }
            Image(
                modifier = Modifier
                    .offset(y = ghostAnimation.dp)
                    .size(244.dp),
                painter = painterResource(R.drawable.ghost),
                contentDescription = "Ghost Image"
            )
            Icon(
                modifier = Modifier
                    .offset(y = -(ghostAnimation/2).dp)
                    .blur(12.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded),
                imageVector = ImageVector.vectorResource(R.drawable.shadow),
                contentDescription = "Ghost Shadow",
                tint = shadowAnimation
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                Modifier
                    .padding(bottom = 50.dp)
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
                        .clickable(onClick = navigateToHome),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "bring my coffee",
                        style = TextStyle(
                            color = Color(0xDEFFFFFF),
                            fontSize = 16.sp,
                            fontFamily = Urbanist,
                            letterSpacing = 0.25.sp
                        )
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.coffee_02),
                        contentDescription = "Coffee Vector",
                        tint = Color(0xDEFFFFFF)
                    )
                }
            }
        }
    }
}

@Composable
private fun StarIcon(modifier: Modifier, color: Color) {
    Icon(
        modifier = modifier,
        imageVector = ImageVector.vectorResource(R.drawable.star),
        contentDescription = "Star Icon",
        tint = color,
    )
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(device = "spec:width=360dp,height=600dp")
@Composable
fun SharedTransitionScope.OnBoardingPreview() {
    AnimatedVisibility(visible = true) {
        OnBoardingContent(
            scope = this@OnBoardingPreview,
            animatedVisibilityScope = this,
            navigateToHome = {})
    }
}