package ___PACKAGE_NAME___.app

import com.google.android.gms.ads.MobileAds
import com.htecgroup.core.presentation.CoreApplication
import ___PACKAGE_NAME___.domain.service.NotificationController
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

    override fun enableMultiDex() = true
    // only-for-databinding: 
    // only-for-databinding: public override fun initViewModelId() {
    // only-for-databinding:     ViewModelIdProvider.viewModelId = BR.vm
    // only-for-databinding: }

    companion object {
        lateinit var app: ___APP_NAME_CAMEL___Application

        fun resources() = app.resources!!
    }
}
