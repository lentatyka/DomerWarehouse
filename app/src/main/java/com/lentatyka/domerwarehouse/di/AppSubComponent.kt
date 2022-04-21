package com.lentatyka.domerwarehouse.di

import com.lentatyka.domerwarehouse.di.login.LoginComponent
import com.lentatyka.domerwarehouse.di.main.MainComponent
import dagger.Module


@Module(subcomponents = [LoginComponent::class, MainComponent::class])
class AppSubComponent