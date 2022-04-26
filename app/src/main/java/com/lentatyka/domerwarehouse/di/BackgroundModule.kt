package com.lentatyka.domerwarehouse.di

import com.lentatyka.domerwarehouse.data.main.background.FirebaseRepository
import com.lentatyka.domerwarehouse.data.main.background.RoomRepository
import dagger.Binds
import dagger.Module

@Module(includes = [RoomModule::class])
abstract class BackgroundModule {

    @Binds
    abstract fun bindFirebaseRepository(repo: FirebaseRepository.Base):FirebaseRepository

    @Binds
    abstract fun bindRoomRepository(repo: RoomRepository.Base):RoomRepository
}