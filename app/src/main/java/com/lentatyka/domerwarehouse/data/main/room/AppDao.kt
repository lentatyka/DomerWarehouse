package com.lentatyka.domerwarehouse.data.main.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE id =:id")
    suspend fun getProductById(id: Int):ProductEntity

    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE :name")
    fun getProductByName(name: String): Flow<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(productList: List<ProductEntity>)
}