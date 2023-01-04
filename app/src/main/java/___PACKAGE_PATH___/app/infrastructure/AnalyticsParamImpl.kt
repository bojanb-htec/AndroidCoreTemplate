package ___PACKAGE_NAME___.app.infrastructure

import com.google.firebase.analytics.FirebaseAnalytics
import ___PACKAGE_NAME___.domain.service.analytics.AnalyticsParam
import javax.inject.Inject

class AnalyticsParamImpl @Inject constructor() : AnalyticsParam {
	override val itemId = FirebaseAnalytics.Param.ITEM_ID
}
