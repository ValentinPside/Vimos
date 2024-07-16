package com.example.vimos.domain.models

import com.example.vimos.data.dto.SecondLevelCategoriesDto

data class FirstLevelCategories(
    val title: String,
    val subCategories: List<SecondLevelCategories>
)
