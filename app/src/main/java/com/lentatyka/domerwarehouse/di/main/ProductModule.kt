package com.lentatyka.domerwarehouse.di.main

import androidx.lifecycle.ViewModel
import com.lentatyka.domerwarehouse.data.main.product.ProductRepository
import com.lentatyka.domerwarehouse.di.viewmodel.ViewModelKey
import com.lentatyka.domerwarehouse.domain.main.ProductInteractor
import com.lentatyka.domerwarehouse.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProductModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindProductViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindProductRepository(repo: ProductRepository.Base):ProductRepository

    @Binds
    abstract fun bindProductInteractor(interactor: ProductInteractor.Base): ProductInteractor

}