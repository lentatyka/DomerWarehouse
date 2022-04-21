package com.lentatyka.domerwarehouse.domain.main.product

import com.lentatyka.domerwarehouse.data.main.product.ProductDataMapper
import com.lentatyka.domerwarehouse.data.main.product.ProductRepository
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import javax.inject.Inject

interface ProductInteractor {
    suspend operator fun invoke(name :String):List<ProductDomain>

    class Base @Inject constructor(
        private val productRepository: ProductRepository,
        private val mapper: ProductDataMapper
    ):ProductInteractor {
        override suspend fun invoke(name: String): List<ProductDomain> =
            productRepository(name).map {
                mapper.map(it)
            }
    }
}