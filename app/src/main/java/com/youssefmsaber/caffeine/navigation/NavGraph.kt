package com.youssefmsaber.caffeine.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.youssefmsaber.caffeine.screen.onboarding.OnBoardingScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(navController: NavHostController) {

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Routes.OnBoarding
        ) {
            composable<Routes.OnBoarding> {
                OnBoardingScreen(
                    animatedVisibilityScope = this@composable
                )
            }
            composable<Routes.Home> {

            }
            composable<Routes.Details> {

            }
            composable<Routes.LoadingOrder> {

            }
            composable<Routes.OrderDone> {

            }
            composable<Routes.ChoseSnack> {

            }
            composable<Routes.Snack> {

            }
        }
    }
}