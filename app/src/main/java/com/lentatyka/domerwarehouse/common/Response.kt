package com.lentatyka.domerwarehouse.common

sealed class Response<out R>{
    data class Success<out T>(val data: T?): Response<T>()
    class Error(val message: String): Response<Nothing>()
    object Loading:Response<Nothing>()
}