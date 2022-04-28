package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models.ClientSubscriptionsListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientSubscriptionsListViewModel
@Inject
constructor(
) : ViewModel() {
    private val _clietnSubscriptionsListViewState: MutableState<ClientSubscriptionsListViewState> = mutableStateOf(ClientSubscriptionsListViewState.Loading)
    val clientSubscriptionsListViewState: State<ClientSubscriptionsListViewState>
        get() = _clietnSubscriptionsListViewState

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val tempState = _clietnSubscriptionsListViewState.value
            _clietnSubscriptionsListViewState.value = ClientSubscriptionsListViewState.Loading
            delay(2000)

            when(tempState) {
                is ClientSubscriptionsListViewState.Loading -> {
                    _clietnSubscriptionsListViewState.value = ClientSubscriptionsListViewState.NoSubscriptions
                }
                is ClientSubscriptionsListViewState.NoSubscriptions -> {
                    _clietnSubscriptionsListViewState.value = ClientSubscriptionsListViewState.OneSubscription(1)
                }
                is ClientSubscriptionsListViewState.OneSubscription -> {
                    _clietnSubscriptionsListViewState.value = ClientSubscriptionsListViewState.SeveralSubscriptions(listOf(1,2,3,4,5))
                }
                is ClientSubscriptionsListViewState.SeveralSubscriptions -> {
                    _clietnSubscriptionsListViewState.value = ClientSubscriptionsListViewState.Error("test")
                }
                is ClientSubscriptionsListViewState.Error -> {
                    _clietnSubscriptionsListViewState.value = ClientSubscriptionsListViewState.NoSubscriptions
                }
            }
        }
    }
}