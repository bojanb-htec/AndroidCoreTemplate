package ___PACKAGE_NAME___.domain.service

interface Crashlytic {

    /**
     * Log caught exception
     */
    fun logException(exception: Throwable)

    /**
     * Log custom message
     */
    fun logMessage(message: String)

    /**
     * Identify user
     */
    fun setUserId(identifier: String)
}
