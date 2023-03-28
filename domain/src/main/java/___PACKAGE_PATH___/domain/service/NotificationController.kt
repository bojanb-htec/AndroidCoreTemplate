package ___PACKAGE_NAME___.domain.service

interface NotificationController {

    fun showNotification(title: String, description: String, channelId: String? = null)

    fun createNotificationChannels()
}
