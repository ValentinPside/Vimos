package com.example.vimos.utils

import com.example.vimos.data.dto.FirstLevelCategoriesDto
import com.example.vimos.data.dto.ImageUrlDto
import com.example.vimos.data.dto.PriceDto
import com.example.vimos.data.dto.ProductDto
import com.example.vimos.data.dto.SecondLevelCategoriesDto
import com.example.vimos.data.dto.SlugDto
import com.example.vimos.data.dto.StrojmaterialDto
import com.example.vimos.data.dto.ThirdLevelCategoriesDto
import com.example.vimos.data.dto.ZeroLevelCategoriesDto
import com.example.vimos.domain.models.FirstLevelCategories
import com.example.vimos.domain.models.ImageUrl
import com.example.vimos.domain.models.Price
import com.example.vimos.domain.models.Product
import com.example.vimos.domain.models.SecondLevelCategories
import com.example.vimos.domain.models.Slug
import com.example.vimos.domain.models.Strojmaterial
import com.example.vimos.domain.models.ThirdLevelCategories
import com.example.vimos.domain.models.ZeroLevelCategories


fun ProductDto.asProduct() = Product(
    title = this.title,
    sku = this.sku,
    units = this.units,
    purchase = this.purchase.asPrice(),
    slug = this.slug,
    images = this.images.asImageUrlList()
)

fun List<ImageUrlDto>.asImageUrlList(): List<ImageUrl> = this.map { it.asImageUrl() }

fun ImageUrlDto.asImageUrl() = ImageUrl(
    original = this.original
)

fun PriceDto.asPrice() = Price(
    price = this.price
)
fun SlugDto.asSlug() = Slug(
    slug = this.slug
)

fun List<SlugDto>.asSlugList(): List<Slug> = this.map { it.asSlug() }
fun StrojmaterialDto.asStrojmaterial() = Strojmaterial(
    title = this.title,
    slug = this.slug,
    subCategories = this.subCategories.asZeroLevelCategories()
)
fun ZeroLevelCategoriesDto.asZeroLevelCategories() = ZeroLevelCategories(
    title = this.title,
    slug = this.slug,
    subCategories = this.subCategories.asFirstLevelCategoriesList()
)

fun FirstLevelCategoriesDto.asFirstLevelCategories() = FirstLevelCategories(
    title = this.title,
    slug = this.slug,
    subCategories = this.subCategories.asSecondLevelCategoriesList()
)

fun SecondLevelCategoriesDto.asSecondLevelCategories() = SecondLevelCategories(
    title = this.title,
    slug = this.slug,
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