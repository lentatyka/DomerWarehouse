package com.lentatyka.domerwarehouse.di.login

import androidx.lifecycle.ViewModel
import com.lentatyka.domerwarehouse.data.login.network.LoginFirebaseApi
import com.lentatyka.domerwarehouse.data.login.network.SignInFirebase
import com.lentatyka.domerwarehouse.data.login.network.SignUpFirebase
import com.lentatyka.domerwarehouse.data.login.repository.SignInRepositoryImpl
import com.lentatyka.domerwarehouse.data.login.repository.SignUpRepositoryImpl
import com.lentatyka.domerwarehouse.di.viewmodel.ViewModelKey
import com.lentatyka.domerwarehouse.domain.login.repository.LoginRepository
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.SignInViewModel
import com.lentatyka.domerwarehouse.presentation.login.viewmodel.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SignIn

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SignUp

@Module
abstract class LoginModule {

    @Binds
    @SignIn
    abstract fun bindSignInRepository(repo: SignInRepositoryImpl): LoginRepository

    @Binds
    @SignUp
    abstract fun bindSignUpRepository(repo: SignUpRepositoryImpl): LoginRepository

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @SignIn
    abstract fun bindSignInFirebaseApi(api: SignInFirebase):LoginFirebaseApi

    @Binds
    @SignUp
    abstract fun bindSignUpFirebaseApi(api: SignUpFirebase):LoginFirebaseApi
}