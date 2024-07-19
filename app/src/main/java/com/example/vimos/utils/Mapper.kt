package com.example.vimos.utils

import com.example.vimos.data.dto.FirstLevelCategoriesDto
import com.example.vimos.data.dto.SecondLevelCategoriesDto
import com.example.vimos.data.dto.StrojmaterialDto
import com.example.vimos.data.dto.ThirdLevelCategoriesDto
import com.example.vimos.data.dto.ZeroLevelCategoriesDto
import com.example.vimos.domain.models.FirstLevelCategories
import com.example.vimos.domain.models.SecondLevelCategories
import com.example.vimos.domain.models.Strojmaterial
import com.example.vimos.domain.models.ThirdLevelCategories
import com.example.vimos.domain.models.ZeroLevelCategories


fun StrojmaterialDto.asStrojmaterial() = Strojmaterial(
    title = this.title,
    subCategories = this.subCategories.asZeroLevelCategories()
)
fun ZeroLevelCategoriesDto.asZeroLevelCategories() = ZeroLevelCategories(
    title = this.title,
    subCategories = this.subCategories.asFirstLevelCategoriesList()
)

fun FirstLevelCategoriesDto.asFirstLevelCategories() = FirstLevelCategories(
    title = this.title,
    subCategories = this.subCategories.asSecondLevelCategoriesList()
)

fun SecondLevelCategoriesDto.asSecondLevelCategories() = SecondLevelCategories(
    title = this.title,
    subCategories = this.subCategories.asThirdLevelCategoriesList()
)

fun ThirdLevelCategoriesDto.asThirdLevelCategories() = ThirdLevelCategories(
    title = this.title,
    slug = this.slug,
    subCategories = this.subCategories
)

fun List<ZeroLevelCategoriesDto>.asZeroLevelCategories(): List<ZeroLevelCategories> = this.map { it.asZeroLevelCategories() }
fun List<ThirdLevelCategoriesDto>.asThirdLevelCategoriesList(): List<ThirdLevelCategories> = this.map { it.asThirdLevelCategories() }

fun List<SecondLevelCategoriesDto>.asSecondLevelCategoriesList(): List<SecondLevelCategories> = this.map { it.asSecondLevelCategories() }

fun List<FirstLevelCategoriesDto>.asFirstLevelCategoriesList(): List<FirstLevelCategories> = this.map { it.asFirstLevelCategories() }