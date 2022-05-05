package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models

import androidx.compose.ui.graphics.ImageBitmap
import java.util.*

sealed class SubscriptionDialogViewState {
    object Hidden : SubscriptionDialogViewState()

    data class Visible(
        val section: String,
        val clientFullName: String,
        val barcodeString: String,
        val barcodeImage: ImageBitmap,
        val startDate: Date,
        val expirationDate: Date
    ) : SubscriptionDialogViewState()
}