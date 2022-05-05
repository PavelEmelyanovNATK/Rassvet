package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases.NavigateToAllSectionsUseCase
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases.PopBackFromSectionsUseCase
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubscriptionsViewModel
@Inject
constructor(
    val subscriptionsNavProvider: SubscriptionsNavProvider,
    private val navigateToSections: NavigateToAllSectionsUseCase,
    private val popBackFromSections: PopBackFromSectionsUseCase
) : ViewModel() {

    fun addSubscriptionCLick() = navigateToSections()
    fun backFromSubscriptionsClick() = popBackFromSections()
}