package com.youssefmsaber.caffeine.model

import androidx.annotation.Keep

@Keep
enum class CoffeeSize(val label: String, val amount: String) {
    Small("S", "150 ML"),
    Medium("M", "200 ML"),
    Large("L", "400 ML")
}