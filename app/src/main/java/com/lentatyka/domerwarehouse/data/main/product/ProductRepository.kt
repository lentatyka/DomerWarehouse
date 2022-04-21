package com.lentatyka.domerwarehouse.data.main.product

import com.lentatyka.domerwarehouse.data.main.room.AppDao
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import javax.inject.Inject

interface ProductRepository {
    suspend operator fun invoke(name: String):List<ProductData>

    class Base @Inject constructor(
        private val appDao: AppDao
    ): ProductRepository {
        override suspend fun invoke(name: String) = appDao.getProductByName(name)
    }
}