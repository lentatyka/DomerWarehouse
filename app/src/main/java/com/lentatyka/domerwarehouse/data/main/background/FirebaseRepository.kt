package com.lentatyka.domerwarehouse.data.main.background

import com.google.firebase.database.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface FirebaseRepository {

    suspend fun getProductList(): List<ProductDto>

    class Base @Inject constructor(
        private val dbReference: DatabaseReference
    ) : FirebaseRepository {
        override suspend fun getProductList(): List<ProductDto> {
            return download()
        }

        private suspend fun download() = suspendCoroutine<List<ProductDto>> { continuation ->
            dbReference.child("nomenclature").addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val productList = mutableListOf<ProductDto>()
                        snapshot.children.forEach {
                            val currentId = it.key!!
                            it.getValue(ProductDto::class.java)?.let {productDto ->
                                productList += productDto.copy(
                                    id = currentId
                                )
                            }
                        }
                        continuation.resume(productList)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        //todo exception
                    }
                }
            )
        }
    }
}