package com.bellminp.domain.di

import com.bellminp.domain.repository.RemoteRepository
import com.bellminp.domain.usecase.RemoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideRemoteUseCase(repository: RemoteRepository): RemoteUseCase {
        return RemoteUseCase(repository)
    }
}