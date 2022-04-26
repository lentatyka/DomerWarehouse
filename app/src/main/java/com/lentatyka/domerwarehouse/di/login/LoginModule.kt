package com.lentatyka.domerwarehouse.di.login

import androidx.lifecycle.ViewModel
import com.lentatyka.domerwarehouse.data.login.LoginRepository
import com.lentatyka.domerwarehouse.di.viewmodel.ViewModelKey
import com.lentatyka.domerwarehouse.domain.login.LoginInteractor
import com.lentatyka.domerwarehouse.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindLoginInteractor(interactor: LoginInteractor.Base): LoginInteractor

    @Binds
    abstract fun bindLoginRepository(repo: LoginRepository.Base): LoginRepository
}