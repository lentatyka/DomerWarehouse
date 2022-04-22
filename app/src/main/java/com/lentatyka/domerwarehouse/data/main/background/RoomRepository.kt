package com.lentatyka.domerwarehouse.data.main.background

import com.lentatyka.domerwarehouse.data.main.room.AppDao
import javax.inject.Inject

interface RoomRepository {
    suspend operator fun invoke(list: List<ProductDto>)

    class Base @Inject constructor(
        private val appDao: AppDao,
        private val mapper: ProductDtoMapper
    ):RoomRepository {
        override suspend fun invoke(list: List<ProductDto>){
            appDao.insertProductList(mapper.map(list))
        }
    }
}