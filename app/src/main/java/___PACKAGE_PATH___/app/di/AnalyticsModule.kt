package ___PACKAGE_NAME___.app.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import ___PACKAGE_NAME___.app.infrastructure.AnalyticsParamImpl
import ___PACKAGE_NAME___.app.infrastructure.FirebaseAnalyticsImpl
import ___PACKAGE_NAME___.domain.service.analytics.Analytics
import ___PACKAGE_NAME___.domain.service.analytics.AnalyticsParam
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AnalyticsModule {

    @Singleton
    @Provides
    fun provideAnalytics(firebaseAnalytics: FirebaseAnalytics): Analytics {
        return FirebaseAnalyticsImpl(firebaseAnalytics)
    }

    @Provides
    fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideAnalyticsParam(): AnalyticsParam {
        return AnalyticsParamImpl()
    }
}
