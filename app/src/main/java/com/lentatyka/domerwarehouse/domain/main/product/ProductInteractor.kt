package com.lentatyka.domerwarehouse.domain.main.product

import com.lentatyka.domerwarehouse.data.main.product.ProductDataMapper
import com.lentatyka.domerwarehouse.data.main.product.ProductRepository
import javax.inject.Inject

interface ProductInteractor {
    suspend operator fun invoke(queryList: List<String>): List<ProductDomain>

    class Base @Inject constructor(
        private val productRepository: ProductRepository,
        private val mapper: ProductDataMapper
    ) : ProductInteractor {
        override suspend fun invoke(queryList: List<String>): List<ProductDomain>{
            val answer = when(queryList.size) {
                1 -> productRepository.getProductByName(queryList[0])
                2 -> productRepository.getProductByNameColor(queryList[0], queryList[1])
                3 -> productRepository.getProductByNameColorSize(queryList[0], queryList[1], queryList[2])
                else -> emptyList()
            }
            return answer.map { mapper.map(it) }
        }
    }
}