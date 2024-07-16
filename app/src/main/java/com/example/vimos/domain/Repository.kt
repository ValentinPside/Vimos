package com.example.vimos.domain

import com.example.vimos.data.dto.ZeroLevelCategoriesDto
import com.example.vimos.domain.models.ZeroLevelCategories

interface Repository {
    suspend fun getZeroLevelList(): ZeroLevelCategories
    suspend fun getCategoryList(categorySlug: String): ZeroLevelCategoriesDto
    suspend fun getProduct(productSlug: String): ZeroLevelCategoriesDto
}