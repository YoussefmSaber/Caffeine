package com.youssefmsaber.caffeine

import com.youssefmsaber.caffeine.model.CoffeeModel

val coffeeCups = listOf<CoffeeModel>(
    CoffeeModel(
        id = 1,
        imageId = R.drawable.espresso,
        emptyCup = R.drawable.espresso_empty_cup,
        cupCover = R.drawable.espresso_cover,
        cupTop = R.drawable.espresso_top,
        name = "Espresso"
    ),
    CoffeeModel(
        id = 2,
        imageId = R.drawable.macchiato,
        emptyCup = R.drawable.macchiato_empty_cup,
        cupCover = R.drawable.macchiato_cover,
        cupTop = R.drawable.macchiato_top,
        name = "Macchiato"
    ),
    CoffeeModel(
        id = 3,
        imageId = R.drawable.latte,
        emptyCup = R.drawable.latte_empty_cup,
        cupCover = R.drawable.lattee_cover,
        cupTop = R.drawable.lattee_top,
        name = "Latte"
    ),
    CoffeeModel(
        id = 4,
        imageId = R.drawable.black,
        emptyCup = R.drawable.black_empty_cup,
        cupCover = R.drawable.black_cover,
        cupTop = R.drawable.black_top,
        name = "Black"
    )
)