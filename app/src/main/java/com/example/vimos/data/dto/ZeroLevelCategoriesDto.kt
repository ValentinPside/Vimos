package com.example.vimos.data.dto

data class ZeroLevelCategoriesDto(
    val title: String,
    val subCategories: List<FirstLevelCategoriesDto>
)
