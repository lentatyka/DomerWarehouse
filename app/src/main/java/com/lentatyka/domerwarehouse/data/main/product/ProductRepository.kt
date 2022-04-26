package com.lentatyka.domerwarehouse.data.main.product

import com.lentatyka.domerwarehouse.data.main.room.AppDao
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import javax.inject.Inject

interface ProductRepository {

    suspend fun getProductByName(name: String):List<ProductData>

    suspend fun getProductByNameColor(name: String, color: String):List<ProductData>

    suspend fun getProductByNameColorSize(name: String, color: String, size: String):List<ProductData>

    class Base @Inject constructor(
        private val appDao: AppDao
    ): ProductRepository {

        override suspend fun getProductByName(name: String): List<ProductData> =
            appDao.getProductByName(name)

        override suspend fun getProductByNameColor(name: String, color: String): List<ProductData> {
            return appDao.getProductByNameColor(name, color)
        }

        override suspend fun getProductByNameColorSize(
            name: String,
            color: String,
            size: String
        ): List<ProductData> {
            return appDao.getProductByNameColorSize(name, color, size)
        }
    }
}