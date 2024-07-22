package com.example.vimos.domain.models

data class Product(
    val title: String,
    val sku: Int,
    val units: String,
    val purchase: Price,
    val slug: String,
    val images: List<ImageUrl>
)
