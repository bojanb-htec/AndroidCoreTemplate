package ___PACKAGE_NAME___.app.infrastructure

import android.app.Activity
import com.google.firebase.analytics.FirebaseAnalytics
import ___PACKAGE_NAME___.domain.service.analytics.Analytics
import ___PACKAGE_NAME___.domain.service.analytics.Event

class FirebaseAnalyticsImpl(private val firebaseAnalytics: FirebaseAnalytics) : Analytics {

	override fun logEvent(event: Event) {
		firebaseAnalytics.logEvent(event.name, event.params)
	}

	override fun setUserProperty(key: String, value: String?) {
		firebaseAnalytics.setUserProperty(key, value)
	}

	override fun setCurrentScreen(
		activity: Activity,
		screenName: String,
		screenClassOverride: String
	) {
		firebaseAnalytics.setCurrentScreen(activity, screenName, screenClassOverride)
	}
}
