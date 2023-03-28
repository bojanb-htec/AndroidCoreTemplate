package ___PACKAGE_NAME___.data.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.htecgroup.core.domain.extension.TAG
import ___PACKAGE_NAME___.domain.service.Logger
import ___PACKAGE_NAME___.domain.service.NotificationController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PushNotificationService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationController: NotificationController

    @Inject
    lateinit var logger: Logger

    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {
            if (it.title.isNullOrEmpty() && it.body.isNullOrEmpty()) return

            logger.d(TAG, "Message Notification Title: ${it.title}")
            logger.d(TAG, "Message Notification Body: ${it.body}")
            notificationController.showNotification(it.title!!, it.body!!)
        }

        message.data.let {
            logger.d(TAG, "Message Data Title: ${it["title"]}")
            logger.d(TAG, "Message Data Body: ${it["body"]}")
        }
    }

    override fun onNewToken(newToken: String) {
        logger.d(TAG, "Refreshed token: $newToken")
        // Persist token to third-party servers.
    }
}
