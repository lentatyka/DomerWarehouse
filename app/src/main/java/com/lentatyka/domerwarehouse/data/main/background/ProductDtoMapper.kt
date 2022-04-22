package com.lentatyka.domerwarehouse.data.main.background

import com.lentatyka.domerwarehouse.common.Mapper
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import javax.inject.Inject

class ProductDtoMapper @Inject constructor() : Mapper<List<ProductDto>, List<ProductData>> {

    override fun map(data: List<ProductDto>) =
        data.map {
            ProductData(
                id = it.id,
                name = it.article,
                color = it.color,
                size = it.size,
                address = it.address
            )
        }
}