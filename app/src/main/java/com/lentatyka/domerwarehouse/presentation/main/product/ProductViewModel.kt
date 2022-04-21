package com.lentatyka.domerwarehouse.presentation.main.product

import androidx.lifecycle.ViewModel
import com.lentatyka.domerwarehouse.domain.main.product.ProductInteractor
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val interactor: ProductInteractor
): ViewModel() {
}