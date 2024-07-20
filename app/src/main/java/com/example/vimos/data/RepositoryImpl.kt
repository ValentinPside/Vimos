package com.example.vimos.data

import com.example.vimos.data.network.NetworkServiceAPI
import com.example.vimos.domain.Repository
import com.example.vimos.domain.models.Slug
import com.example.vimos.domain.models.Strojmaterial
import com.example.vimos.domain.models.ZeroLevelCategories
import com.example.vimos.utils.asSlugList
import com.example.vimos.utils.asStrojmaterial
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: NetworkServiceAPI): Repository {
    override suspend fun getZeroLevelList(): List<Strojmaterial> {
        return withContext(Dispatchers.IO) {
            val categoriesRemote = api.getZeroLevelDtoList()
            categoriesRemote.map { it.asStrojmaterial() }
        }
    }

    override suspend fun getCategoryList(categorySlug: String): List<Slug> {
        return withContext(Dispatchers.IO) {
            val categoriesRemote = api.getCategoryList(categorySlug)
            categoriesRemote.asSlugList()
        }
    }

    override suspend fun getProduct(productSlug: String): ZeroLevelCategories {
        TODO("Not yet implemented")
    }
}