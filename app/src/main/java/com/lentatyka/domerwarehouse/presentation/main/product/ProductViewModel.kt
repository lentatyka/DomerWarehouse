package com.lentatyka.domerwarehouse.presentation.main.product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lentatyka.domerwarehouse.domain.main.product.ProductDomain
import com.lentatyka.domerwarehouse.domain.main.product.ProductInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val interactor: ProductInteractor
): ViewModel() {
    init {
        Log.d("TAG", "DSdasd")
        viewModelScope.launch(Dispatchers.IO){
           val d = interactor("TEDST")
            Log.d("TAG", "${d.size}")
        }
    }

    fun test(){

    }
}