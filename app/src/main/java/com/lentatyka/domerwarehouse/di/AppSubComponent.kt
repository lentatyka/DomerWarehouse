package com.lentatyka.domerwarehouse.di

import com.lentatyka.domerwarehouse.di.login.LoginComponent
import dagger.Module


@Module(subcomponents = [LoginComponent::class])
class AppSubComponent