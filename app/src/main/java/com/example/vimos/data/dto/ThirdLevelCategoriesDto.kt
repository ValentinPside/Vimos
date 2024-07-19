package com.example.vimos.data.dto

import com.example.vimos.domain.Categories

data class ThirdLevelCategoriesDto(
    val title: String,
    val slug: String,
    val subCategories: List<Categories>
)
