package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases

import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavProvider
import javax.inject.Inject


class NavigateToSectionDetailsUseCase
@Inject
constructor(
    private val subscriptionsNavProvider: SubscriptionsNavProvider
) {
    operator fun invoke(id: Int) {
        subscriptionsNavProvider.navigateTo(SubscriptionsDestinations.SubscriptionDetails(id))
    }
}