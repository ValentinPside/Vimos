package com.example.vimos.data.network

import com.example.vimos.data.dto.SlugDto
import com.example.vimos.data.dto.StrojmaterialDto
import com.example.vimos.data.dto.ZeroLevelCategoriesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkServiceAPI {

    @GET("categories/-/catalog")
    suspend fun getZeroLevelDtoList(): List<StrojmaterialDto>

    @GET("products/{categorySlug}/catalog")
    suspend fun getCategoryList(@Path("categorySlug") categorySlug: String): List<SlugDto>

    @GET("products/{productSlug}")
    suspend fun getProduct(@Path("productSlug") productSlug: String): ZeroLevelCategoriesDto
}