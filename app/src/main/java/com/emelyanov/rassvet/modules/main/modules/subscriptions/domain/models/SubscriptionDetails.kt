package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models

import androidx.compose.ui.graphics.ImageBitmap
import java.util.*

data class SubscriptionDetails (
    val id: Int,
    val section: String,
    val barcodeString: String,
    val barcodeImage: ImageBitmap,
    val startDate: Date,
    val expirationDate: Date
)