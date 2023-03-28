package ___PACKAGE_NAME___.app.di

import ___PACKAGE_NAME___.app.infrastructure.LoggerImpl
import ___PACKAGE_NAME___.domain.service.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoggerModule {

    @Singleton
    @Binds
    fun provideLogger(logger: LoggerImpl): Logger
}
