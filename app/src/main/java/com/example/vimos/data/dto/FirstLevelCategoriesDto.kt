package com.example.vimos.data.dto

data class FirstLevelCategoriesDto(
    val title: String,
    val slug: String,
    val subCategories: List<SecondLevelCategoriesDto>
)
