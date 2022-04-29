package com.emelyanov.rassvet.navigation.subscriptions

import com.emelyanov.rassvet.shared.domain.utils.BaseNavProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class SubscriptionsListNavProvider(startDestination: SubscriptionsListDestinations) :
    BaseNavProvider<SubscriptionsListDestinations>(startDestination)