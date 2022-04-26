package com.lentatyka.domerwarehouse.domain.main.editor


import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.data.main.editor.EditorRepository
import com.lentatyka.domerwarehouse.data.main.product.ProductDataMapper
import com.lentatyka.domerwarehouse.domain.main.product.ProductDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

interface EditorInteractor {

    suspend fun getProductById(id: String):ProductDomain

    fun insertProduct(data: ProductDomain): Flow<Response<Nothing>>

    class Base @Inject constructor(
        private val repository: EditorRepository.Base,
        private val mapper: ProductDataMapper
    ):EditorInteractor {
        override suspend fun getProductById(id: String): ProductDomain {
            return mapper.map(
                repository.getProductById(id)
            )
        }

        override fun insertProduct(data: ProductDomain) = flow {
            emit(Response.Loading)
            try {
                repository.insertProduct(data)
                Response.Success(null)
            }catch (e: IOException){
                Response.Error(e.localizedMessage ?: "Error! Data has not been written!")
            }
        }
    }
}