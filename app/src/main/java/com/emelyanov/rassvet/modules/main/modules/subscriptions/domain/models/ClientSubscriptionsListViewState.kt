package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import java.util.*

sealed class ClientSubscriptionsListViewState {
    object Loading : ClientSubscriptionsListViewState()

    data class OneSubscription(
        val section: String,
        val clientFullName: String,
        val barcodeString: String,
        val barcodeImage: ImageBitmap,
        val startDate: Date,
        val expirationDate: Date
    ) : ClientSubscriptionsListViewState()

    data class SeveralSubscriptions(
        val subscriptions: List<Subscription>,
        val onSubscriptionClick: (Int) -> Unit,
        val onDismissRequest: () -> Unit,
        val subscriptionDialogViewState: MutableState<SubscriptionDialogViewState>
    ) : ClientSubscriptionsListViewState()

    data class NoSubscriptions(
        val onSubscriptionsClick: () -> Unit
    ) : ClientSubscriptionsListViewState()

    data class Error(val message: String) : ClientSubscriptionsListViewState()
}