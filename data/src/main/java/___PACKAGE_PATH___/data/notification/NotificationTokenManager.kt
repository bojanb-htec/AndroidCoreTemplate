package ___PACKAGE_NAME___.data.notification

import com.google.firebase.messaging.FirebaseMessaging
import com.htec.core.domain.Result
import ___PACKAGE_NAME___.data.repositories.contracts.INotificationTokenStore
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NotificationTokenManager @Inject constructor(
	private val firebaseMessaging: FirebaseMessaging
) : INotificationTokenStore {

	override suspend fun getNotificationToken(): Result<String> = suspendCoroutine { coroutine ->
		firebaseMessaging.token.addOnCompleteListener {
			if (it.isSuccessful && it.result != null) {
				coroutine.resume(Result.Success(it.result))
			} else {
				coroutine.resume(
					Result.Error(it.exception ?: Exception("Failed to fetch Firebase token."))
				)
			}
		}
	}

	override suspend fun unregisterNotificationToken(): Result<Unit> =
		suspendCoroutine { coroutine ->
			firebaseMessaging.deleteToken().addOnCompleteListener {
				if (it.isSuccessful) {
					coroutine.resume(Result.Success(Unit))
				} else {
					coroutine.resume(
						Result.Error(
							it.exception ?: Exception("Failed to unregister Firebase token.")
						)
					)
				}
			}
		}
}
