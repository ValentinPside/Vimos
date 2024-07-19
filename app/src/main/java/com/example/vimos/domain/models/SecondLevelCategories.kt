package com.example.vimos.domain.models

import com.example.vimos.data.dto.ThirdLevelCategoriesDto
import com.example.vimos.domain.Categories

data class SecondLevelCategories(
    override val title: String,
    override val subCategories: List<ThirdLevelCategories>
): Categories
