package com.lentatyka.domerwarehouse.domain.main


data class ProductDomain(
    val id: String,
    val name: String,
    val color: String,
    val size: String,
    val address: List<String>?
)