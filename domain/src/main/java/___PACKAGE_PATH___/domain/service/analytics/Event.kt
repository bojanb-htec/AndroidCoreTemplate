package ___PACKAGE_NAME___.domain.service.analytics

import android.os.Bundle
import androidx.annotation.Size

class Event private constructor(val name: String, val params: Bundle) {

	class Builder(@param:Size(min = 1L, max = 40L) private val eventName: String) {

		private val params: Bundle = Bundle()

		fun setStringParam(key: String, value: String) = apply { params.putString(key, value) }

		fun setIntegerParam(key: String, value: Int) = apply { params.putInt(key, value) }

		fun setBooleanParam(key: String, value: Boolean) = apply { params.putBoolean(key, value) }

		fun setDoubleParam(key: String, value: Double) = apply { params.putDouble(key, value) }

		fun setFloatParam(key: String, value: Float) = apply { params.putFloat(key, value) }

		fun setLongParam(key: String, value: Long) = apply { params.putLong(key, value) }

		fun build() = Event(eventName, params)
	}
}
