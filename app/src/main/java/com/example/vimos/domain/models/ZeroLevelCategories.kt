package com.example.vimos.domain.models

import com.example.vimos.data.dto.FirstLevelCategoriesDto

data class ZeroLevelCategories(
    val subCategories: List<FirstLevelCategories>
)
