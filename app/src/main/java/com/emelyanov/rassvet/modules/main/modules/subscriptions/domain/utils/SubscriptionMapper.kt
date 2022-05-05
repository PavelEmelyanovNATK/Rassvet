package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.utils

import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models.Subscription
import com.emelyanov.rassvet.shared.domain.models.responseModels.SubscriptionResponse
import com.emelyanov.rassvet.shared.domain.utils.getDateFromString
import javax.inject.Inject


class SubscriptionMapper
@Inject
constructor(

) {
    fun map(subscriptionResponse: SubscriptionResponse) : Subscription
    = subscriptionResponse.let {
        Subscription(
            id = it.id,
            section = it.sectionName,
            barcodeString = it.barcodeString,
            startDate = getDateFromString(it.startDate),
            expirationDate = getDateFromString(it.expirationDate)
        )
    }
}