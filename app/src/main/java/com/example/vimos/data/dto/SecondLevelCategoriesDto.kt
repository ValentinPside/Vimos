package com.example.vimos.data.dto

data class SecondLevelCategoriesDto(
    val title: String,
    val slug: String,
    val subCategories: List<ThirdLevelCategoriesDto>
)
