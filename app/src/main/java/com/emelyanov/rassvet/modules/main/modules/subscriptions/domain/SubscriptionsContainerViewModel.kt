package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubscriptionsContainerViewModel
@Inject
constructor(
    val subscriptionsListNavProvider: SubscriptionsListNavProvider
) : ViewModel()