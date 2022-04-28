package com.lentatyka.domerwarehouse.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.lentatyka.domerwarehouse.domain.main.ProductInteractor
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: ProductInteractor
) : ViewModel() {



    private val queryList = MutableLiveData(listOf(""))

    val result = queryList.switchMap { str->
        interactor.getList(str)
    }

    fun search(query: String) {
        val queryList = query.split(" ").map {
            "%$it%"
        }
        this.queryList.value = queryList
    }
}