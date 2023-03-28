package ___PACKAGE_NAME___.presentation.base

import com.htecgroup.core.domain.extension.TAG
import com.htecgroup.core.domain.extension.collect
import com.htecgroup.core.domain.extension.collectResult
import com.htecgroup.core.presentation.model.DataUiState.Error
import com.htecgroup.core.presentation.model.DataUiState.Idle
import com.htecgroup.core.presentation.viewmodel.CoreViewModel
import ___PACKAGE_NAME___.domain.service.Logger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

abstract class BaseViewModel<T : Any> : CoreViewModel<T>() {

    @Inject
    lateinit var logger: Logger

    suspend fun Result<T>.collectToUiState(
        onSuccess: (T?) -> Unit = { uiState = Idle(it) },
        onError: (Throwable?) -> Unit = {
            logger.e(TAG, it)
            uiState = Error(errorMessage = it?.message)
        }
    ) {
        collect(onSuccess = onSuccess, onError = onError)
    }

    suspend fun Flow<Result<T?>>.collectToUiState(
        onSuccess: (T?) -> Unit = { uiState = Idle(it) },
        onError: (Throwable?) -> Unit = {
            logger.e(TAG, it)
            uiState = Error(errorMessage = it?.message)
        }
    ) {
        collectResult(onSuccess, onError)
    }
}
