package ___PACKAGE_NAME___.app.infrastructure

import android.util.Log
import ___PACKAGE_NAME___.app.BuildConfig
import ___PACKAGE_NAME___.domain.service.Logger
import javax.inject.Inject

class LoggerImpl @Inject constructor() : Logger {

	override fun d(tag: String, message: String?, error: Throwable?) {
		(message ?: error?.message ?: error?.localizedMessage ?: "")
			.takeIf { BuildConfig.DEBUG }
			?.let {
				Log.d(tag, it, error)
			}
	}

	override fun e(tag: String, error: Throwable?, message: String?) {
		(message ?: error?.message ?: error?.localizedMessage ?: "")
			.takeIf { BuildConfig.DEBUG }
			?.let {
				Log.e(tag, it, error)
			}
	}

	override fun i(tag: String, message: String?, error: Throwable?) {
		(message ?: error?.message ?: error?.localizedMessage ?: "")
			.takeIf { BuildConfig.DEBUG }
			?.let {
				Log.i(tag, it, error)
			}
	}

	override fun v(tag: String, message: String?, error: Throwable?) {
		(message ?: error?.message ?: error?.localizedMessage ?: "")
			.takeIf { BuildConfig.DEBUG }
			?.let {
				Log.v(tag, it, error)
			}
	}

	override fun w(tag: String, message: String?, error: Throwable?) {
		(message ?: error?.message ?: error?.localizedMessage ?: "")
			.takeIf { BuildConfig.DEBUG }
			?.let {
				Log.w(tag, it, error)
			}
	}
}
