package com.example.vimos.domain

import com.example.vimos.domain.models.Product
import com.example.vimos.domain.models.Slug
import com.example.vimos.domain.models.Strojmaterial

interface Repository {
    suspend fun getZeroLevelList(): List<Strojmaterial>
    suspend fun getCategoryList(categorySlug: String): List<Slug>
    suspend fun getProduct(productSlug: String): Product
}