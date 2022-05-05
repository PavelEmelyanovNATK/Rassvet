package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases

import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListNavProvider
import javax.inject.Inject


class PopBackFromSectionsUseCase
@Inject
constructor(
    private val subscriptionsListNavProvider: SubscriptionsListNavProvider
) {
    operator fun invoke() {
        subscriptionsListNavProvider.navigateTo(SubscriptionsListDestinations.PopBack)
    }
}