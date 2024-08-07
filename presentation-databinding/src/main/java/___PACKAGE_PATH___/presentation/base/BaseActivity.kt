package ___PACKAGE_NAME___.presentation.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.htecgroup.androidcore.presentation.CoreActivity
import ___PACKAGE_NAME___.domain.service.Logger
import javax.inject.Inject

abstract class BaseActivity<BindingT : ViewDataBinding, ViewModelT : ViewModel> :
    CoreActivity<BindingT, ViewModelT>() {

    @Inject
    lateinit var logger: Logger
}
