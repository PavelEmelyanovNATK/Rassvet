package com.emelyanov.rassvet.modules.main.domain.usecases

import com.emelyanov.rassvet.navigation.main.MainDestinations
import com.emelyanov.rassvet.navigation.main.MainNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavProvider
import javax.inject.Inject


class NavigateToSubscriptionsUseCase
@Inject
constructor(
    private val mainNavProvider: MainNavProvider,
    private val subscriptionsNavProvider: SubscriptionsNavProvider,
    private val subscriptionsListNavProvider: SubscriptionsListNavProvider
) {
    operator fun invoke() {
        mainNavProvider.navigateTo(MainDestinations.Subscriptions)
        subscriptionsNavProvider.navigateTo(SubscriptionsDestinations.SubscriptionsList)
        subscriptionsListNavProvider.navigateTo(SubscriptionsListDestinations.ClientSubscriptions)
    }
}