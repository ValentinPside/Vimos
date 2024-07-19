package com.example.vimos.domain

import com.example.vimos.domain.models.SecondLevelCategories

interface Categories {
    val title: String
    val subCategories: List<Categories>
}