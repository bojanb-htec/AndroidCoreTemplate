package ___PACKAGE_NAME___.data.repositories.contracts

import com.htec.core.domain.Result

interface INotificationTokenStore {
	suspend fun getNotificationToken(): Result<String>
	suspend fun unregisterNotificationToken(): Result<Unit>
}
