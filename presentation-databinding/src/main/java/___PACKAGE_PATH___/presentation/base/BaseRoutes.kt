package ___PACKAGE_NAME___.presentation.base

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavController
import com.htecgroup.core.presentation.routes.Routes
import javax.inject.Inject

open class BaseRoutes @Inject constructor(
    var activity: Activity,
    override val navController: NavController? = null
) : Routes {

    inline fun <reified ValueT> navigateToActivityWithClearTop(clazz: Class<ValueT>) {
        activity.run {
            startActivity(
                Intent(this, clazz)
                    .apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK
                    }
            )
            finish()
        }
    }

    fun navigateBack() = navController?.navigateUp()
}
