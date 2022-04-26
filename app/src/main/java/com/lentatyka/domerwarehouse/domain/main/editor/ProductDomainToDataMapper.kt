package com.lentatyka.domerwarehouse.domain.main.editor

import com.lentatyka.domerwarehouse.common.Mapper
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import com.lentatyka.domerwarehouse.domain.main.product.ProductDomain
import javax.inject.Inject

class ProductDomainToDataMapper @Inject constructor():Mapper<ProductDomain, ProductData> {

    override fun map(data: ProductDomain): ProductData {
        return ProductData(
            id = data.id,
            name = data.name,
            color = data.color,
            size = data.size,
            address = data.address
        )
    }
}