package com.example.vimos.domain

import com.example.vimos.domain.models.Slug
import com.example.vimos.domain.models.Strojmaterial
import com.example.vimos.domain.models.ZeroLevelCategories

interface Repository {
    suspend fun getZeroLevelList(): List<Strojmaterial>
    suspend fun getCategoryList(categorySlug: String): List<Slug>
    suspend fun getProduct(productSlug: String): ZeroLevelCategories
}