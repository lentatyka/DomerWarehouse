package com.lentatyka.domerwarehouse.data.main.background



data class ProductDto(
    val id: String ="",
    val name: String ="",
    val color: String? = null,
    val size: String? = null,
    val address: List<String>? = null
)