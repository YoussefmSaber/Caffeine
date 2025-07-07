package com.youssefmsaber.caffeine.screen.snack

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youssefmsaber.caffeine.R
import com.youssefmsaber.caffeine.screen.composable.TopApp
import com.youssefmsaber.caffeine.screen.modifier.dropShadow
import com.youssefmsaber.caffeine.ui.theme.Brown
import com.youssefmsaber.caffeine.ui.theme.Gray
import com.youssefmsaber.caffeine.ui.theme.Sniglet
import com.youssefmsaber.caffeine.ui.theme.Urbanist

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SnackScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    imageId: Int,
    onNavigateBack: () -> Unit,
) {
    SnackScreenContent(
        onNavigateBack = onNavigateBack,
        imageId = imageId,
        animatedVisibilityScope = animatedVisibilityScope,
        sharedTransitionScope = this
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SnackScreenContent(
    onNavigateBack: () -> Unit,
    imageId: Int,
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.coffee_beans),
                    contentDescription = "Coffee Beans",
                    tint = Brown
                )
                BasicText(
                    text = "More Espresso, Less Depresso",
                    style = TextStyle(
                        color = Brown,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Sniglet,
                        fontSize = 20.sp,
                        letterSpacing = 0.25.sp
                    )
                )
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.coffee_beans),
                    contentDescription = "Coffee Beans",
                    tint = Brown
                )
            }
            Image(
                modifier = Modifier
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "snack/${imageId}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .padding(top = 64.dp)
                    .size(width = 300.dp, height = 310.dp),
                painter = painterResource(imageId),
                contentDescription = "Snack Image",
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BasicText(
                    text = "Bon appétit",
                    style = TextStyle(
                        color = Color(0xCC1F1F1F),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Urbanist,
                        letterSpacing = 0.25.sp
                    )
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.magic_wand_01),
                    contentDescription = "Magic Wand",
                    tint = Color(0xCC1F1F1F)
                )
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
                        .clickable(onClick = onNavigateBack),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Thank youuu",
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
fun SnackScreenPreview() {
//    SnackScreenContent(
//        onNavigateBack = {},
//        R.drawable.cinnamon_roll,
//        animatedVisibilityScope = animatedVisibilityScope,
//        sharedTransitionScope = this
//    )
}