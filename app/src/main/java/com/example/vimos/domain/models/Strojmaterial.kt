package com.example.vimos.domain.models

import com.example.vimos.domain.Categories

data class Strojmaterial(
    override val title: String,
    override val slug: String,
    override val subCategories: List<ZeroLevelCategories>
) : Categories
