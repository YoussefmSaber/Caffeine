package com.youssefmsaber.caffeine.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.youssefmsaber.caffeine.model.CoffeeModel
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
            startDestination = Routes.OnBoarding
        ) {
            composable<Routes.OnBoarding> {
                OnBoardingScreen(
                    animatedVisibilityScope = this@composable
                )
            }
            composable<Routes.Home> {
                HomeScreen(animatedVisibilityScope = this@composable)
            }
            composable<Routes.Details> {
                val args = it.toRoute<Routes.Details>()
                val coffee = CoffeeModel(
                    imageId = args.imageId,
                    name = args.name
                )
                DetailsScreen(
                    animatedVisibilityScope = this@composable,
                    coffee = coffee
                )
            }
            composable<Routes.LoadingOrder> {
                val args = it.toRoute<Routes.LoadingOrder>()
                LoadingOrderScreen(
                    animatedVisibilityScope = this@composable,
                    coffeeImageId = args.imageId,
                    coffeeSize = args.size
                )
            }
            composable<Routes.OrderDone> {
                val args = it.toRoute<Routes.OrderDone>()
                OrderDoneScreen(
                    animatedVisibilityScope = this@composable,
                    coffeeImageId = args.imageId,
                    coffeeSize = args.size
                )
            }
            composable<Routes.ChoseSnack> {
                ChoseSnackScreen(animatedVisibilityScope = this@composable)
            }
            composable<Routes.Snack> {
                val args = it.toRoute<Routes.Snack>()
                SnackScreen(
                    animatedVisibilityScope = this@composable,
                    imageId = args.imageId
                )
            }
        }
    }
}