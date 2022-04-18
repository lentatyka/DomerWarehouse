package com.lentatyka.domerwarehouse.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module

import com.google.firebase.database.FirebaseDatabase
import dagger.Provides

//todo replace it to Main sub component
@Module
class FirebaseModule {

    @Provides
    fun provideFirebaseInstance() = FirebaseDatabase.getInstance().reference

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}