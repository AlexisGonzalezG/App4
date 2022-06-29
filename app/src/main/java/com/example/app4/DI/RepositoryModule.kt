package com.example.app4.DI

import com.example.app4.network.JokesRepository
import com.example.app4.network.JokesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds

    abstract fun providesJokesRepository(
       jokesRepositoryImpl: JokesRepositoryImpl
    ):JokesRepository
}