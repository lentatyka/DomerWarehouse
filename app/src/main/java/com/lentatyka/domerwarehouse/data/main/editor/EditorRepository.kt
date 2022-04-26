package com.lentatyka.domerwarehouse.data.main.editor

import com.google.firebase.database.DatabaseReference
import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.data.main.room.AppDao
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import com.lentatyka.domerwarehouse.domain.main.editor.ProductDomainToDataMapper
import com.lentatyka.domerwarehouse.domain.main.product.ProductDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface EditorRepository {

    suspend fun getProductById(id: String): ProductData

    suspend fun insertProduct(data: ProductDomain)

    class Base @Inject constructor(
        private val appDao: AppDao,
        private val domainMapper: ProductDomainToDataMapper,
        private val fbDatabase: DatabaseReference
    ) : EditorRepository {

        override suspend fun getProductById(id: String): ProductData = appDao.getProductById(id)

        override suspend fun insertProduct(data: ProductDomain) {
            val productData = domainMapper.map(data)
            insertProductRemote(productData)
            appDao.insertProduct(productData)
        }

        private fun insertProductRemote(data: ProductData) =
            fbDatabase.child("nomenclature")
                .child(data.id)
                .setValue(data)
                .addOnFailureListener {
                    throw IOException(it.localizedMessage)
                }
    }

}
