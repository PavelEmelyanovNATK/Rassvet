package com.emelyanov.rassvet.modules.firstboot.domain.usecases

import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.emelyanov.rassvet.navigation.firstboot.FirstBootNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListNavProvider
import javax.inject.Inject

class PopDetailsBackUseCase
@Inject
constructor(
    private val firstBootNavProvider: FirstBootNavProvider
) {
    operator fun invoke() {
        firstBootNavProvider.navigateTo(FirstBootDestinations.PopBack)
    }
}