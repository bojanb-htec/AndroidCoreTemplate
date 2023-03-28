package ___PACKAGE_NAME___.data.repositories.contracts

interface INotificationTokenStore {
    suspend fun getNotificationToken(): Result<String>
    suspend fun unregisterNotificationToken(): Result<Unit>
}
