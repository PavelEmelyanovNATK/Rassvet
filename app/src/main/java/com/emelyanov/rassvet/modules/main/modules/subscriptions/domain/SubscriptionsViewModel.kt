package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubscriptionsViewModel
@Inject
constructor(
    val subscriptionsNavProvider: SubscriptionsNavProvider,
    val subscriptionsListNavProvider: SubscriptionsListNavProvider
) : ViewModel() {

}