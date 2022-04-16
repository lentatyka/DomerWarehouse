package com.lentatyka.domerwarehouse.common

sealed class Responce<T>{
    data class Success<T>(val data: T): Responce<T>()
    class Error(val message: String): Responce<Nothing>()
    object Loading:Responce<Nothing>()
}
