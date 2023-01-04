package ___PACKAGE_NAME___.presentation.utils.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ___PACKAGE_NAME___.domain.service.NotificationController
import ___PACKAGE_NAME___.presentation.R
import ___PACKAGE_NAME___.presentation.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val NOTIFICATION_ID = 12345

class NotificationControllerImpl @Inject constructor(
	@ApplicationContext private val context: Context
) : NotificationController {

	private val defaultChannelId = context.getString(R.string.default_notification_channel_id)

	override fun createNotificationChannels() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val channelName = context.getString(R.string.default_notification_channel_name)
			context.getSystemService(NotificationManager::class.java)?.createNotificationChannel(
				NotificationChannel(
					defaultChannelId, channelName, NotificationManager.IMPORTANCE_HIGH
				)
			)
		}
	}

	override fun showNotification(title: String, description: String, channelId: String?) {
		val cnlId = channelId ?: defaultChannelId
		NotificationManagerCompat.from(context)
			.notify(NOTIFICATION_ID, createNotification(title, description, cnlId))
	}

	private fun createNotification(
		title: String,
		description: String,
		channelId: String
	): Notification {
		val intent = Intent(context, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
		}

		val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
		} else {
			PendingIntent.FLAG_UPDATE_CURRENT
		}

		return NotificationCompat.Builder(context, channelId)
			.setSmallIcon(R.drawable.ic_launcher_foreground)
			.setContentTitle(title)
			.setContentText(description)
			.setContentIntent(
				PendingIntent.getActivity(context, 0, intent, flag)
			)
			.setAutoCancel(true)
			.build()
	}
}
