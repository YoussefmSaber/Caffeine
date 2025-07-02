package com.youssefmsaber.caffeine.model

import androidx.annotation.DrawableRes

data class CoffeeModel(
    val id: Int,
    @field:DrawableRes val imageId: Int,
    val name: String
)