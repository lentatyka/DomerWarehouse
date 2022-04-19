package com.lentatyka.domerwarehouse.common

sealed class Response<T>{
    data class Success<T>(val data: T?): Response<T>()
    class Error(val message: String): Response<Nothing>()
    object Loading:Response<Nothing>()
}
