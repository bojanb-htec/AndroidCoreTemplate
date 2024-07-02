package ___PACKAGE_NAME___.app

// only-for-google-ads: import com.google.android.gms.ads.MobileAds
import com.htecgroup.androidcore.presentation.CoreApplication
// only-for-push-notifications: import ___PACKAGE_NAME___.domain.service.NotificationController
// only-for-databinding: import ___PACKAGE_NAME___.presentation.viewmodel.ViewModelIdProvider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ___APP_NAME_CAMEL___Application : CoreApplication() {
// only-for-push-notifications: 
// only-for-push-notifications:     @Inject
// only-for-push-notifications:     lateinit var notificationController: NotificationController

    init {
        app = this
    }

    override fun onCreate() {
        super.onCreate()
        /*if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }*/

        // Stetho.initializeWithDefaults(this);

        // only-for-push-notifications: notificationController.createNotificationChannels()
        // only-for-google-ads: MobileAds.initialize(this)
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
