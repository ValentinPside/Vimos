package com.example.vimos.domain.models

import com.example.vimos.data.dto.SecondLevelCategoriesDto
import com.example.vimos.domain.Categories

data class FirstLevelCategories(
    override val title: String,
    override val slug: String,
    override val subCategories: List<SecondLevelCategories>
): Categories
