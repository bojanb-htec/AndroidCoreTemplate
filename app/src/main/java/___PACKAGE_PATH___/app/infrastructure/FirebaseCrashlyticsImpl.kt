package ___PACKAGE_NAME___.app.infrastructure

import com.google.firebase.crashlytics.FirebaseCrashlytics
import ___PACKAGE_NAME___.domain.service.Crashlytic
import javax.inject.Inject

class FirebaseCrashlyticsImpl @Inject constructor() : Crashlytic {

    private val firebaseCrashlytics = FirebaseCrashlytics.getInstance()

    override fun logException(exception: Throwable) {
        firebaseCrashlytics.recordException(exception)
    }

    override fun logMessage(message: String) {
        firebaseCrashlytics.log(message)
    }

    override fun setUserId(identifier: String) {
        firebaseCrashlytics.setUserId(identifier)
    }
}
