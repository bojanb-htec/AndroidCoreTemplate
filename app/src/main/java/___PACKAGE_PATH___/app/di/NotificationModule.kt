package ___PACKAGE_NAME___.app.di

import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import ___PACKAGE_NAME___.data.notification.NotificationTokenManager
import ___PACKAGE_NAME___.data.repositories.contracts.INotificationTokenStore
import ___PACKAGE_NAME___.domain.service.NotificationController
import ___PACKAGE_NAME___.presentation.utils.notification.NotificationControllerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

    @Provides
    fun provideFirebaseMessaging() = FirebaseMessaging.getInstance()

    @Singleton
    @Provides
    fun provideNotificationTokenStore(firebaseMessaging: FirebaseMessaging): INotificationTokenStore =
        NotificationTokenManager(firebaseMessaging)

    @Singleton
    @Provides
    fun provideNotificationController(@ApplicationContext context: Context):
        NotificationController = NotificationControllerImpl(context)
}
