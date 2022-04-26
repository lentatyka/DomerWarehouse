package com.lentatyka.domerwarehouse.presentation.main.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lentatyka.domerwarehouse.domain.main.editor.EditorInteractor
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AddressChangeVMFactory @AssistedInject constructor(
    private val interactor: EditorInteractor.Base,
    @Assisted("productId") private val id: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddressChangeViewModel(interactor, id) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("productId") id: String): AddressChangeVMFactory
    }
}