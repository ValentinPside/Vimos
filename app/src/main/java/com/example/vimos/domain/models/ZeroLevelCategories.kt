package com.example.vimos.domain.models

import com.example.vimos.domain.Categories

data class ZeroLevelCategories(
    override val title: String,
    override val slug: String,
    override val subCategories: List<FirstLevelCategories>
): Categories
