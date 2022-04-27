package com.lentatyka.domerwarehouse.domain.main

import com.lentatyka.domerwarehouse.data.main.product.ProductRepository
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import javax.inject.Inject

interface ProductInteractor {
    suspend operator fun invoke(queryList: List<String>): List<ProductData>

    class Base @Inject constructor(
        private val productRepository: ProductRepository
    ) : ProductInteractor {
        override suspend fun invoke(queryList: List<String>): List<ProductData> {
            return when (queryList.size) {
                1 -> productRepository.getProductByName(queryList[0])
                2 -> productRepository.getProductByNameColor(queryList[0], queryList[1])
                3 -> productRepository.getProductByNameColorSize(
                    queryList[0],
                    queryList[1],
                    queryList[2]
                )
                else -> emptyList()
            }
        }
    }
}