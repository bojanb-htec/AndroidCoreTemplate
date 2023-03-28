package ___PACKAGE_NAME___.domain.service

interface Logger {
    fun d(tag: String, message: String? = "", error: Throwable? = null)

    fun e(tag: String, error: Throwable? = null, message: String? = "")

    fun i(tag: String, message: String? = "", error: Throwable? = null)

    fun v(tag: String, message: String? = "", error: Throwable? = null)

    fun w(tag: String, message: String? = "", error: Throwable? = null)
}
