package com.lentatyka.domerwarehouse.presentation.main.product

import androidx.lifecycle.LiveData
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

    private val _productList = MutableLiveData<List<ProductDomain?>>()
    val productList:LiveData<List<ProductDomain?>> get() = _productList


    fun search(query: String) {
        if(query.isNotEmpty()){
            viewModelScope.launch(Dispatchers.IO){
                val result = interactor("%$query%")
                _productList.postValue(result)
            }
        }else{
            //Не будет делать запрос с пустум полем. Вроде логично =)
            _productList.value = emptyList()
        }

    }
}