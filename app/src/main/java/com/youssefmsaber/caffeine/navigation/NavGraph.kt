package com.youssefmsaber.caffeine.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.youssefmsaber.caffeine.screen.chose_snack.ChoseSnackScreen
import com.youssefmsaber.caffeine.screen.details.DetailsScreen
import com.youssefmsaber.caffeine.screen.home.HomeScreen
import com.youssefmsaber.caffeine.screen.loading_order.LoadingOrderScreen
import com.youssefmsaber.caffeine.screen.onboarding.OnBoardingScreen
import com.youssefmsaber.caffeine.screen.order_done.OrderDoneScreen
import com.youssefmsaber.caffeine.screen.snack.SnackScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = OnBoarding
        ) {
            composable<OnBoarding> {
                OnBoardingScreen(
                    animatedVisibilityScope = this@composable,
                    navigateOnClick = {
                        navController.navigate(Home)
                    }
                )
            }
            composable<Home> {
                HomeScreen(
                    animatedVisibilityScope = this@composable,
                    navigateToDetails = { coffeeId ->
                        navController.navigate(Details(coffeeId))
                    }
                )
            }
            composable<Details> {
                val args = it.toRoute<Details>()
                DetailsScreen(
                    animatedVisibilityScope = this@composable,
                    coffeeId = args.coffeeId,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onOrderFinish = { coffeeId, coffeeSize ->
                        navController.navigate(LoadingOrder(coffeeId, coffeeSize))
                    }
                )
            }
            composable<LoadingOrder> {
                val args = it.toRoute<LoadingOrder>()
                LoadingOrderScreen(
                    animatedVisibilityScope = this@composable,
                    coffeeId = args.imageId,
                    coffeeSize = args.size
                )
            }
            composable<OrderDone> {
                val args = it.toRoute<OrderDone>()
                OrderDoneScreen(
                    animatedVisibilityScope = this@composable,
                    coffeeImageId = args.imageId,
                    coffeeSize = args.size
                )
            }
            composable<ChoseSnack> {
                ChoseSnackScreen(animatedVisibilityScope = this@composable)
            }
            composable<Snack> {
                val args = it.toRoute<Snack>()
                SnackScreen(
                    animatedVisibilityScope = this@composable,
                    imageId = args.imageId
                )
            }
        }
    }
}