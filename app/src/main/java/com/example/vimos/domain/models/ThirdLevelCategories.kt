package com.example.vimos.domain.models

import com.example.vimos.domain.Categories


data class ThirdLevelCategories(
    override val title: String,
    val slug: String,
    override val subCategories: List<Categories>
): Categories
