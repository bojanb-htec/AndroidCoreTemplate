package ___PACKAGE_NAME___.domain.service.analytics

import android.app.Activity
import androidx.annotation.Size

interface Analytics {

    fun logEvent(event: Event)

    fun setUserProperty(@Size(min = 1L, max = 24L) key: String, @Size(max = 36L) value: String?)

    fun setCurrentScreen(activity: Activity, screenName: String, screenClassOverride: String)
}
