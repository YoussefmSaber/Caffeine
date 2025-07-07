package com.youssefmsaber.caffeine.navigation

import com.youssefmsaber.caffeine.model.CoffeeSize
import kotlinx.serialization.Serializable

    @Serializable
    object OnBoarding

    @Serializable
    object Home

    @Serializable
    data class Details(val coffeeId: Int)

    @Serializable
    data class LoadingOrder(val coffeeId: Int, val size: CoffeeSize)

    @Serializable
    data class OrderDone(val coffeeId: Int, val size: CoffeeSize)

    @Serializable
    object ChoseSnack

    @Serializable
    data class Snack(val snackId: Int)

