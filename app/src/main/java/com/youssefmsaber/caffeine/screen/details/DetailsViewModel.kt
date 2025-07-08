package com.youssefmsaber.caffeine.screen.details

import androidx.lifecycle.ViewModel
import com.youssefmsaber.caffeine.model.CoffeeLevel
import com.youssefmsaber.caffeine.model.CoffeeSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsViewModel : ViewModel() {

    private val _selectedSize = MutableStateFlow(CoffeeSize.Medium)
    val selectedSize: StateFlow<CoffeeSize> = _selectedSize.asStateFlow()

    private val _coffeeLevel = MutableStateFlow(CoffeeLevel.Low)
    val coffeeLevel: StateFlow<CoffeeLevel> = _coffeeLevel.asStateFlow()

    fun onSizeSelected(size: CoffeeSize) {
        _selectedSize.value = size
    }

    fun getAmount(size: CoffeeSize): String = size.amount

    fun setBeanLevel(level: CoffeeLevel) {
        _coffeeLevel.value = level
    }

    fun isCoffeeVisible(
        newSize: CoffeeSize,
        newLevel: CoffeeLevel,
        oldSize: CoffeeSize,
        oldLevel: CoffeeLevel
    ): Boolean {
        return if ((newSize == CoffeeSize.Medium && oldSize == CoffeeSize.Small)
            || (newLevel == CoffeeLevel.Medium && oldLevel == CoffeeLevel.Low)
        ) {
            true
        } else if ((newSize == CoffeeSize.Large && (oldSize == CoffeeSize.Small || oldSize == CoffeeSize.Medium))
            || (newLevel == CoffeeLevel.High && (oldLevel == CoffeeLevel.Low || oldLevel == CoffeeLevel.Medium))
        ) {
            true
        } else {
            false
        }
    }
}