package com.lentatyka.domerwarehouse.di.login

import androidx.lifecycle.ViewModel
import com.lentatyka.domerwarehouse.data.LoginRepositoryImpl
import com.lentatyka.domerwarehouse.di.ViewModelKey
import com.lentatyka.domerwarehouse.domain.login.LoginRepository
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.LoginViewModel
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.SignInViewModel
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @Binds
    abstract fun bindLoginRepository(repo: LoginRepositoryImpl): LoginRepository

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel
}