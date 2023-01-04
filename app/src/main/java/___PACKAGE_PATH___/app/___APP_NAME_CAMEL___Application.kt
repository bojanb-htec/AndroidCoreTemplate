package ___PACKAGE_NAME___.app

import com.google.android.gms.ads.MobileAds
import com.htec.core.presentation.CoreApplication
import com.htec.core.presentation.viewmodel.ViewModelIdProvider
import ___PACKAGE_NAME___.domain.service.NotificationController
import ___PACKAGE_NAME___.presentation.BR
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ___APP_NAME_CAMEL___Application : CoreApplication() {

	@Inject
	lateinit var notificationController: NotificationController

	init {
		app = this
	}

	override fun onCreate() {
		super.onCreate()
		/*if (!LeakCanary.isInAnalyzerProcess(this)) {
			LeakCanary.install(this);
		}*/

		// Stetho.initializeWithDefaults(this);

		notificationController.createNotificationChannels()
		MobileAds.initialize(this)
	}

	public override fun initViewModelId() {
		ViewModelIdProvider.viewModelId = BR.vm
	}

	override fun enableMultiDex() = true

	companion object {
		lateinit var app: ___APP_NAME_CAMEL___Application

		fun resources() = app.resources!!
	}
}
