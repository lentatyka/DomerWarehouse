package com.lentatyka.domerwarehouse.data.main.product

import com.lentatyka.domerwarehouse.common.Mapper
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import com.lentatyka.domerwarehouse.domain.main.product.ProductDomain
import javax.inject.Inject

class ProductDataMapper @Inject constructor(): Mapper<ProductData, ProductDomain> {
    override fun map(data: ProductData): ProductDomain = ProductDomain(
        id = data.id,
        name = data.name,
        color = data.color ?: "-",
        size = data.size ?: "-",
        address = data.address
    )
}