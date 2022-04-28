package com.lentatyka.domerwarehouse.data.main.room


import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE :name")
    fun getProductByName(name: String): PagingSource<Int, ProductData>

    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE :name AND color LIKE :color")
    fun getProductByNameColor(name: String, color: String): PagingSource<Int, ProductData>

    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE :name AND color LIKE :color AND size LIKE :size")
    fun getProductByNameColorSize(
        name: String,
        color: String,
        size: String
    ): PagingSource<Int, ProductData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(productList: List<ProductData>)
}