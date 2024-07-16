package com.example.vimos.domain.models

import com.example.vimos.data.dto.StrojmaterialDto

data class ThirdLevelCategories(
    val title: String,
    val subCategories: List<Strojmaterial>
)
