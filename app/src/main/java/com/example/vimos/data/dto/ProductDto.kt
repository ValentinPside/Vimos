package com.example.vimos.data.dto

data class ProductDto(
    val title: String,
    val slug: String,
    val sku: Int,
    val units: String,
    val purchase: PriceDto,
    val images: List<ImageUrlDto>
)