package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models

sealed class ClientSubscriptionsListViewState {
    object Loading : ClientSubscriptionsListViewState()
    data class OneSubscription(val id: Int) : ClientSubscriptionsListViewState()
    data class SeveralSubscriptions(val ids: List<Int>) : ClientSubscriptionsListViewState()
    object NoSubscriptions : ClientSubscriptionsListViewState()
    data class Error(val message: String) : ClientSubscriptionsListViewState()
}