package com.emelyanov.rassvet.modules.firstboot.domain.usecases

import com.emelyanov.rassvet.navigation.core.CoreDestinations
import com.emelyanov.rassvet.navigation.core.CoreNavProvider
import javax.inject.Inject

class NavigateToAuthUseCase
@Inject
constructor(
    private val coreNavProvider: CoreNavProvider
) {
    operator fun invoke() {
        coreNavProvider.navigateTo(CoreDestinations.Authorization)
    }
}