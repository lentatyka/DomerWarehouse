package com.lentatyka.domerwarehouse.domain.main

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.lentatyka.domerwarehouse.data.main.product.ProductRepository
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import javax.inject.Inject

interface ProductInteractor {
    fun getList(queryList: List<String>): LiveData<PagingData<ProductData>>

    class Base @Inject constructor(
        private val productRepository: ProductRepository
    ) : ProductInteractor {
        override fun getList(queryList: List<String>): LiveData<PagingData<ProductData>> {
            return Pager(
                config = PagingConfig(
                    pageSize = 20,
                    maxSize = 100,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    when (queryList.size) {
                        1 -> productRepository.getProductByName(queryList[0])
                        2 -> productRepository.getProductByNameColor(queryList[0], queryList[1])
                        else -> productRepository.getProductByNameColorSize(
                            queryList[0],
                            queryList[1],
                            queryList[2]
                        )
                    }
                }
            ).liveData
        }
    }
}