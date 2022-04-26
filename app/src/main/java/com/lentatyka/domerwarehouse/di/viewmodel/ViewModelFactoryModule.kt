package com.lentatyka.domerwarehouse.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.lentatyka.domerwarehouse.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory

}