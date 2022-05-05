package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models

import java.util.*

data class Subscription(
    val id: Int,
    val section: String,
    val barcodeString: String,
    val startDate: Date,
    val expirationDate: Date
)