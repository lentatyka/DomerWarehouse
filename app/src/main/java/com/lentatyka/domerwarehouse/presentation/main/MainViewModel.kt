package com.lentatyka.domerwarehouse.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lentatyka.domerwarehouse.data.main.room.ProductData
import com.lentatyka.domerwarehouse.domain.main.ProductInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: ProductInteractor
) : ViewModel() {

    private val _productList = MutableLiveData<List<ProductData>>()
    val productList: LiveData<List<ProductData>> get() = _productList


    fun search(query: String) {
        val queryList = query.split(" ").map {
            "%$it%"
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor(queryList)
            _productList.postValue(result)
        }
    }
}