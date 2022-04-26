package com.lentatyka.domerwarehouse.presentation.main.editor

import androidx.lifecycle.*
import com.lentatyka.domerwarehouse.domain.main.editor.EditorInteractor
import com.lentatyka.domerwarehouse.domain.main.product.ProductDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddressChangeViewModel(
    private val interactor: EditorInteractor.Base,
    private val id: String
) : ViewModel() {

    private val _product = MutableLiveData<List<String>>()
    val product: LiveData<List<String>> get() = _product
    lateinit var addressList: MutableList<String>

    init {
        viewModelScope.launch(Dispatchers.IO){
            addressList.addAll(interactor.getProductById(id).address!!)
            _product.postValue(
                addressList
            )
        }
    }

    fun deleteAddress(position: Int){
        addressList.removeAt(position)
        _product.value = addressList
    }

    fun addAddress(address: String){
        addressList += address
    }





//    private val _product = MutableLiveData<ProductDomain>()
//    val product: LiveData<ProductDomain> get() = _product
//
//    fun saveData() = flow{
//        interactor.i
//    }
//
//    fun deleteAddress(position: Int){
//        _product.value?.address?.
//    }
}