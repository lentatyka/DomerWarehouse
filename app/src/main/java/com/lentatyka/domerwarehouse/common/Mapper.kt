package com.lentatyka.domerwarehouse.common

interface Mapper<T, V> {
    fun map(data: T):V
}