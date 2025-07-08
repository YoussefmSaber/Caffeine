package com.youssefmsaber.caffeine.model

import androidx.annotation.DrawableRes

data class CoffeeModel(
    val id: Int,
    @field:DrawableRes val imageId: Int,
    @field:DrawableRes val emptyCup: Int,
    @field:DrawableRes val cupCover: Int,
    @field:DrawableRes val cupTop: Int,
    val name: String
)