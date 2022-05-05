package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models.ClientSubscriptionsListViewState
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models.SubscriptionDialogViewState
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases.GetClientFullNameUseCase
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases.GetClientSubscriptionsUseCase
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases.GetSubscriptionDetailsUseCase
import com.emelyanov.rassvet.shared.domain.usecases.NavigateToAllSubscriptionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientSubscriptionsListViewModel
@Inject
constructor(
    private val getClientSubscriptions: GetClientSubscriptionsUseCase,
    private val getSubscriptionDetails: GetSubscriptionDetailsUseCase,
    private val navigateToSections: NavigateToAllSubscriptionsUseCase,
    private val getClientFullName: GetClientFullNameUseCase
) : ViewModel() {
    private val _viewState: MutableState<ClientSubscriptionsListViewState> = mutableStateOf(ClientSubscriptionsListViewState.Loading)
    val viewState: State<ClientSubscriptionsListViewState>
        get() = _viewState

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _viewState.value = ClientSubscriptionsListViewState.Loading

            try{
                getClientSubscriptions().let {
                    when {
                        it.isEmpty() -> {
                            _viewState.value = ClientSubscriptionsListViewState.NoSubscriptions(
                                onSubscriptionsClick = navigateToSections::invoke
                            )
                        }
                        it.count() == 1 -> {
                            getSubscriptionDetails(it[0].id, it).let { sub ->
                                _viewState.value = ClientSubscriptionsListViewState.OneSubscription(
                                    section = sub.section,
                                    barcodeString = sub.barcodeString,
                                    barcodeImage = sub.barcodeImage,
                                    startDate = sub.startDate,
                                    expirationDate = sub.expirationDate,
                                    clientFullName = getClientFullName()
                                )
                            }
                        }
                        else -> {
                            _viewState.value = ClientSubscriptionsListViewState.SeveralSubscriptions(
                                subscriptions = it,
                                onSubscriptionClick = ::onSubscriptionClick,
                                onDismissRequest = ::onDismiss,
                                subscriptionDialogViewState = mutableStateOf(SubscriptionDialogViewState.Hidden)
                            )
                        }
                    }
                }
            } catch (ex: Exception) {
                _viewState.value = ClientSubscriptionsListViewState.Error(
                    message = ex.message ?: "Неописанная ошибка: ${ex::class.java.simpleName}"
                )
            }
        }
    }

    private fun onSubscriptionClick(id: Int) {
        val state = _viewState.value

        if(state is ClientSubscriptionsListViewState.SeveralSubscriptions){
            try{
                getSubscriptionDetails(id, state.subscriptions).let { sub ->
                    state.subscriptionDialogViewState.value = SubscriptionDialogViewState.Visible(
                        section = sub.section,
                        barcodeString = sub.barcodeString,
                        barcodeImage = sub.barcodeImage,
                        startDate = sub.startDate,
                        expirationDate = sub.expirationDate,
                        clientFullName = getClientFullName()
                    )
                }
            } catch (ex: Exception) {
                _viewState.value = ClientSubscriptionsListViewState.Error(
                    message = ex.message ?: "Неописанная ошибка: ${ex::class.java.simpleName}"
                )
            }
        }
    }

    private fun onDismiss() {
        val state = _viewState.value

        if(state is ClientSubscriptionsListViewState.SeveralSubscriptions){
            state.subscriptionDialogViewState.value = SubscriptionDialogViewState.Hidden
        }
    }
}