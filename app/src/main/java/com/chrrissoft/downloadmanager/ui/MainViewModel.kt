package com.chrrissoft.downloadmanager.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.lifecycle.ViewModel
import com.chrrissoft.downloadmanager.entities.DownloadQuery
import com.chrrissoft.downloadmanager.entities.DownloadRequest
import com.chrrissoft.downloadmanager.entities.ResState.Companion.asSnackbar
import com.chrrissoft.downloadmanager.entities.ResState.Companion.map
import com.chrrissoft.downloadmanager.entities.ResState.Success
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.ListingPageEvent.OnChangeQuery
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.ListingPageEvent.OnRemove
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.ListingPageEvent.OnRunQuery
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.RequestPageEvent.OnChangeRequest
import com.chrrissoft.downloadmanager.ui.MainScreenEvent.RequestPageEvent.OnLaunch
import com.chrrissoft.downloadmanager.ui.entities.SnackbarData
import com.chrrissoft.downloadmanager.ui.entities.SnackbarData.MessageType
import com.chrrissoft.downloadmanager.usecases.interfaces.EnqueueDownloadUseCase
import com.chrrissoft.downloadmanager.usecases.interfaces.GetDownloadItemsUseCase
import com.chrrissoft.downloadmanager.usecases.interfaces.GetDownloadRequestsUseCase
import com.chrrissoft.downloadmanager.usecases.interfaces.RemoveDownloadUseCase
import com.chrrissoft.downloadmanager.utils.ComposeUtils.show
import com.chrrissoft.downloadmanager.utils.DownloadRequestUtils.countText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.viewModelScope as scope

@HiltViewModel
class MainViewModel @Inject constructor(
    private val RemoveDownloadUseCase: RemoveDownloadUseCase,
    private val EnqueueDownloadUseCase: EnqueueDownloadUseCase,
    private val GetDownloadItemsUseCase: GetDownloadItemsUseCase,
    private val GetDownloadRequestsUseCase: GetDownloadRequestsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val stateFlow = _state.asStateFlow()
    private val state get() = stateFlow.value
    private val _page get() = state.page
    private val _listing get() = state.listingPage
    private val _request get() = state.requestPage
    private val _snackbar get() = state.snackbar
    private val _query get() = _listing.query
    private val _list get() = _listing.list
    private val _ids get() = _listing.ids

    private val handler = DownloadScreenEventHandler()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showSnackbar(message = throwable.message ?: "Unknown error", MessageType.Error)
    }

    private var itemsJob: Job? = null

    init {
        loadIds()
        loadItems(state.listingPage.query)
    }

    fun handleEvent(event: MainScreenEvent) {
        try {
            event.resolve(handler)
        } catch (e: Throwable) {
            showSnackbar(message = e.message ?: "Unknown error", type = MessageType.Error)
        }
    }

    inner class DownloadScreenEventHandler {
        val request = RequestPageEventHandler()
        val listing = ListingPageEventHandler()

        inner class RequestPageEventHandler {
            fun onEvent(event: OnChangeRequest) {
                updateState(request = _request.copy(request = event.data))
            }

            fun onEvent(event: OnLaunch) {
                scope.launch(exceptionHandler) {
                    EnqueueDownloadUseCase(event.data).collect { res ->
                        showSnackbar(res.countText(operation = "started"), res.asSnackbar())
                        if (res !is Success) return@collect
                        val ids =
                            _ids.map { it.plus(res.data.keys.mapTo(mutableSetOf()) { it.id }) }
                        if (ids !is Success) return@collect
                        loadItems(_query.copy(ids = ids.data))
                    }
                }
            }
        }

        inner class ListingPageEventHandler {
            fun onEvent(event: OnChangeQuery) {
                updateState(listing = _listing.copy(query = event.data))
            }

            fun onEvent(event: OnRemove) {
                scope.launch(exceptionHandler) {
                    RemoveDownloadUseCase(event.data.map { DownloadRequest(id = it.id) }).collect { res ->
                        showSnackbar(res.countText(operation = "deleted"), res.asSnackbar())
                    }
                    loadItems(_query)
                }
            }

            fun onEvent(event: OnRunQuery) = loadItems(event.data)
        }

        fun onEvent(event: MainScreenEvent.OnChangePage) {
            updateState(page = event.data)
        }
    }

    private fun loadIds() {
        scope.launch {
            GetDownloadRequestsUseCase().collect { res ->
                val ids = res.map { state -> state.mapTo(mutableSetOf()) { it.id } }
                updateState(listing = _listing.copy(ids = ids))
            }
        }
    }

    private fun loadItems(query: DownloadQuery) {
        itemsJob?.cancel()
        itemsJob = scope.launch(exceptionHandler) {
            GetDownloadItemsUseCase(query).collect {
                updateState(listing = _listing.copy(list = it))
            }
        }
    }

    private fun updateState(
        page: MainScreenState.Page = _page,
        snackbar: SnackbarData = _snackbar,
        request: MainScreenState.RequestPageState = _request,
        listing: MainScreenState.ListingPageState = _listing
    ) {
        _state.update {
            it.copy(
                page = page,
                requestPage = request,
                listingPage = listing,
                snackbar = snackbar
            )
        }
    }

    private fun showSnackbar(message: String, type: MessageType) {
        updateState(snackbar = _snackbar.copy(type = type))
        _snackbar.state.show(scope, message)
    }

    private fun showSnackbarForResult(
        message: String,
        type: MessageType,
        action: String,
        dispatcher: CoroutineContext? = null,
        onSuccessDismiss: () -> Unit,
    ) {
        updateState(snackbar = _snackbar.copy(type = type))
        scope.launch(exceptionHandler) {
            val res = _snackbar.state.show(
                message,
                actionLabel = action,
                duration = SnackbarDuration.Long
            )
            if (res == SnackbarResult.ActionPerformed && type == MessageType.Success) {
                withContext(context = dispatcher ?: this.coroutineContext) {
                    onSuccessDismiss()
                }
            }
        }
    }
}
