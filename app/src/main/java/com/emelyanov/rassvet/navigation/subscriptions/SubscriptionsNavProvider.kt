package com.emelyanov.rassvet.navigation.subscriptions

import com.emelyanov.rassvet.navigation.trainings.TrainingsDestinations
import com.emelyanov.rassvet.shared.domain.utils.BaseNavProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class SubscriptionsNavProvider() :
    BaseNavProvider<SubscriptionsDestinations>()