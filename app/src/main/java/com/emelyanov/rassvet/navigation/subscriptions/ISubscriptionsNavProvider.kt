package com.emelyanov.rassvet.navigation.subscriptions

import com.emelyanov.rassvet.navigation.trainings.TrainingsDestinations
import kotlinx.coroutines.flow.SharedFlow

interface ISubscriptionsNavProvider {
    fun getDestinationFlow(): SharedFlow<SubscriptionsDestinations>
    fun navigateToSubscriptions()
    fun navigateToDetails(id: Int)
    fun popBack()
}