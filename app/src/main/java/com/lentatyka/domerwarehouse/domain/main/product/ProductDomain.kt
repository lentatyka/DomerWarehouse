package com.lentatyka.domerwarehouse.domain.main.product


data class ProductDomain(
    val id: Int,
    val name: String,
    val color: String,
    val size: String,
    val address: String?
)