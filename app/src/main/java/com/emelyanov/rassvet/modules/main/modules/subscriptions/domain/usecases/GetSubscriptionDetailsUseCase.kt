package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases

import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models.Subscription
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models.SubscriptionDetails
import javax.inject.Inject


class GetSubscriptionDetailsUseCase
@Inject
constructor(
    private val generateBarcodeImage: GenerateBarcodeImageUseCase
) {
    operator fun invoke(id: Int, subscriptions: List<Subscription>) : SubscriptionDetails
    = subscriptions.first { it.id == id }
        .let { sub ->
            SubscriptionDetails(
                id = sub.id,
                section = sub.section,
                barcodeString = sub.barcodeString,
                barcodeImage = generateBarcodeImage(sub.barcodeString),
                startDate = sub.startDate,
                expirationDate = sub.expirationDate
            )
        }
}