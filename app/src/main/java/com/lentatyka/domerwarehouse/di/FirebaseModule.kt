package com.lentatyka.domerwarehouse.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {

    @Provides
    fun provideFirebaseInstance(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference.root
    }

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}