package com.lentatyka.domerwarehouse.data.main.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE id =:id")
    suspend fun getProductById(id: Int): ProductData

    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE :name")
    suspend fun getProductByName(name: String): List<ProductData>

    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE :name AND color LIKE :color")
    suspend fun getProductByNameColor(name: String, color: String): List<ProductData>

    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE :name AND color LIKE :color AND size LIKE :size")
    suspend fun getProductByNameColorSize(
        name: String,
        color: String,
        size: String
    ): List<ProductData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(productList: List<ProductData>)
}