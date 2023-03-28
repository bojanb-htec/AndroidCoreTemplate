package ___PACKAGE_NAME___.presentation.base

import com.htecgroup.core.presentation.viewmodel.CoreViewModel
import ___PACKAGE_NAME___.domain.service.Logger
import javax.inject.Inject

abstract class BaseViewModel<ActionCodeT> : CoreViewModel<ActionCodeT>() {

    @Inject
    lateinit var logger: Logger
}
