package com.youssefmsaber.caffeine.navigation

import com.youssefmsaber.caffeine.model.CoffeeSize
import kotlinx.serialization.Serializable

    @Serializable
    object OnBoarding

    @Serializable
    object Home

    @Serializable
    data class Details(val imageId: Int, val name: String)

    @Serializable
    data class LoadingOrder(val imageId: Int, val size: CoffeeSize)

    @Serializable
    data class OrderDone(val imageId: Int, val size: CoffeeSize)

    @Serializable
    object ChoseSnack

    @Serializable
    data class Snack(val imageId: Int)

