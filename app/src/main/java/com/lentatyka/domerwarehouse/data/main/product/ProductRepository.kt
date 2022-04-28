package com.lentatyka.domerwarehouse.data.main.product

import android.util.Log
import androidx.paging.PagingSource
import com.lentatyka.domerwarehouse.data.main.room.AppDao
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import javax.inject.Inject

interface ProductRepository {

    fun getProductByName(name: String): PagingSource<Int, ProductData>

    fun getProductByNameColor(name: String, color: String): PagingSource<Int, ProductData>

    fun getProductByNameColorSize(
        name: String, color: String, size: String
    ): PagingSource<Int, ProductData>

    class Base @Inject constructor(
        private val appDao: AppDao
    ) : ProductRepository {

        override fun getProductByName(name: String): PagingSource<Int, ProductData> {
            Log.d("TAG", "REPO: $name")
            return appDao.getProductByName(name)

        }

        override fun getProductByNameColor(
            name: String,
            color: String
        ): PagingSource<Int, ProductData> {
            return appDao.getProductByNameColor(name, color)
        }

        override fun getProductByNameColorSize(
            name: String,
            color: String,
            size: String
        ): PagingSource<Int, ProductData> {
            return appDao.getProductByNameColorSize(name, color, size)
        }
    }
}