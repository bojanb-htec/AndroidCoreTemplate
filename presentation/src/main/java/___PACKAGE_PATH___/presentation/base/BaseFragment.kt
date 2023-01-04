package ___PACKAGE_NAME___.presentation.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.htec.core.presentation.fragment.CoreFragment
import com.htec.core.presentation.routes.Routes
import ___PACKAGE_NAME___.domain.service.Logger
import javax.inject.Inject

abstract class BaseFragment<BindingT : ViewDataBinding, ViewModelT : ViewModel, RouteT : Routes> :
	CoreFragment<BindingT, ViewModelT, RouteT>() {

	@Inject
	lateinit var logger: Logger
}
