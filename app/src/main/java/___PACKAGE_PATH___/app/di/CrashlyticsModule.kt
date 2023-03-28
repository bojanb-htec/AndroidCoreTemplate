package ___PACKAGE_NAME___.app.di

import ___PACKAGE_NAME___.app.infrastructure.FirebaseCrashlyticsImpl
import ___PACKAGE_NAME___.domain.service.Crashlytic
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CrashlyticsModule {

    @Singleton
    @Binds
    fun provideCrashlytics(firebaseCrashlytics: FirebaseCrashlyticsImpl): Crashlytic
}
