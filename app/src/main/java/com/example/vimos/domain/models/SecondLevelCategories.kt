package com.example.vimos.domain.models

import com.example.vimos.data.dto.ThirdLevelCategoriesDto

data class SecondLevelCategories(
    val title: String,
    val subCategories: List<ThirdLevelCategories>
)
