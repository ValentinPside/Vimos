package com.example.vimos.data

import com.example.vimos.data.network.NetworkServiceAPI
import com.example.vimos.domain.Repository
import com.example.vimos.domain.models.ZeroLevelCategories
import com.example.vimos.utils.asZeroLevelCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: NetworkServiceAPI): Repository {
    override suspend fun getZeroLevelList(): ZeroLevelCategories {
        return withContext(Dispatchers.IO) {
            val categoriesRemote = api.getZeroLevelDtoList()
            categoriesRemote.asZeroLevelCategories()
        }
    }

    override suspend fun getCategoryList(categorySlug: String): ZeroLevelCategories {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(productSlug: String): ZeroLevelCategories {
        TODO("Not yet implemented")
    }
}