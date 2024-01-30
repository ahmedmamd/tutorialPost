package com.posts.base

import ERROR_API
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posts.data.model.NetworkState
import com.posts.utils.handleException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


open class BaseViewModell : ViewModel() {

    private var dispatcher: Job? = null

    /// un auth sharedFlow
    private val _unAuthorizedFlow = MutableSharedFlow<Boolean>()
    internal val unAuthorizedFlow get() =  _unAuthorizedFlow.asSharedFlow()

    // maintenance sharedFlow
    private val _maintenanceFlow = MutableSharedFlow<Boolean>()
    internal val maintenanceFlow = _maintenanceFlow.asSharedFlow()

    /// update sharedFlow
    private val _updateFlow = MutableSharedFlow<Boolean>()
    internal val updateFlow = _updateFlow.asSharedFlow()

    // network connection flow
    private val _connectionErrorFlow = MutableSharedFlow<Boolean>()
    internal val connectionErrorFlow = _connectionErrorFlow.asSharedFlow()


    protected fun <T> executeSharedFlow(
        sharedFlow: MutableSharedFlow<NetworkState>,
        request: Flow<T>
    ) {
        dispatcher = viewModelScope.launch(handlerSharedException(sharedFlow)) {
            request.onStart {

            }.onCompletion {
                    sharedFlow.emit(NetworkState.StopLoading) }
                .catch {
                    it.printStackTrace()
                    sharedFlow.emit(NetworkState.Error(it)) }
                .collectLatest {
                    sharedFlow.emit(NetworkState.Success(it)) }
        }
    }

    private fun handlerSharedException(state: MutableSharedFlow<NetworkState>): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
            when (throwable.message) {
                ERROR_API.UNAUTHRIZED -> {
                    _unAuthorizedFlow.tryEmit(true)
                }
                ERROR_API.MAINTENANCE -> {
                    _maintenanceFlow.tryEmit(true)
                }
                ERROR_API.CONNECTION_ERROR -> {
                    _connectionErrorFlow.tryEmit(true)
                }
                else -> {
                    state.tryEmit(NetworkState.Error(throwable.handleException()))
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        dispatcher?.cancel()
    }

}