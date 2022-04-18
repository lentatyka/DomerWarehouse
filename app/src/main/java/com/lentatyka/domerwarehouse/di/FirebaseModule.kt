package com.lentatyka.domerwarehouse.di

import dagger.Module

import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase
import dagger.Provides

@Module
class FirebaseModule {

    @Provides
    fun provideFirebaseInstance() = FirebaseDatabase.getInstance().reference

//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    val d = FirebaseAuth.
//    DatabaseReference myRef = database.getReference("message");
}