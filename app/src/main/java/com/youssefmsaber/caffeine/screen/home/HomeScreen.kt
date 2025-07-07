package com.youssefmsaber.caffeine.screen.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.youssefmsaber.caffeine.coffeeCups
import com.youssefmsaber.caffeine.screen.composable.TopApp
import com.youssefmsaber.caffeine.screen.modifier.dropShadow
import com.youssefmsaber.caffeine.ui.theme.Gray
import com.youssefmsaber.caffeine.ui.theme.Urbanist
import kotlin.math.absoluteValue

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    HomeScreenContent(
        modifier = modifier,
        sharedTransitionScope = this,
        animatedVisibilityScope = animatedVisibilityScope,
        onItemClick = navigateToDetails
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun HomeScreenContent(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val horizontalPager =
        rememberPagerState(initialPage = 3, pageCount = { coffeeCups.size })
    with(sharedTransitionScope) {
        Column(
            modifier = modifier
                .background(Color.White)
                .windowInsetsPadding(WindowInsets.systemBars)
                .fillMaxSize(),
        ) {
            TopApp(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
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
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 80.dp)
            ) {
                BasicText(
                    text = "Good Morning",
                    style = TextStyle(
                        color = Color(0xFFB3B3B3),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        letterSpacing = 0.25.sp
                    )
                )
                BasicText(
                    text = "Hamsa ☀",
                    style = TextStyle(
                        color = Color(0xFF3B3B3B),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        letterSpacing = 0.25.sp
                    )
                )
                BasicText(
                    text = "What would you like to drink today?",
                    style = TextStyle(
                        color = Color(0xCC1F1F1F),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 0.25.sp
                    )
                )
            }
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = horizontalPager,
                contentPadding = PaddingValues(horizontal = 60.dp),
                verticalAlignment = Alignment.Bottom,
                beyondViewportPageCount = 3,
                pageSpacing = (-60).dp
            ) { page ->

                val pageOffset =
                    ((page - horizontalPager.currentPage) - horizontalPager.currentPageOffsetFraction).absoluteValue

                val scale = lerp(0.6f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                val verticalOffset = lerp(80f, 0f, 1f - pageOffset.coerceIn(0f, 1f))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .scale(scale)
                            .offset(y = verticalOffset.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(key = "image/${page}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .size(300.dp),
                            painter = painterResource(coffeeCups[page].imageId),
                            contentDescription = "Coffee Cup"
                        )
                        Image(
                            modifier = Modifier
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(key = "logo/${page}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .align(Alignment.Center)
                                .offset(y = 30.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.the_chance_coffe_big),
                            contentDescription = "Big Coffee Image",
                        )
                    }
                    BasicText(
                        modifier = Modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "text/${page}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                        text = coffeeCups[page].name,
                        style = TextStyle(
                            color = Color(0xFF1F1F1F),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Urbanist
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
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
                        .clickable(onClick = { onItemClick(horizontalPager.currentPage) }),
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showSystemUi = true, device = "spec:width=360dp,height=800dp")
@Composable
fun HomeScreenPreview() {
//    HomeScreenContent()
}