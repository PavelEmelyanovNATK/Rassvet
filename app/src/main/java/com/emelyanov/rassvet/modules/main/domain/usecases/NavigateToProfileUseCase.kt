package com.emelyanov.rassvet.modules.main.domain.usecases

import com.emelyanov.rassvet.navigation.main.MainDestinations
import com.emelyanov.rassvet.navigation.main.MainNavProvider
import com.emelyanov.rassvet.navigation.profile.ProfileDestinations
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import javax.inject.Inject


class NavigateToProfileUseCase
@Inject
constructor(
    private val mainNavProvider: MainNavProvider,
    private val profileNavProvider: ProfileNavProvider
) {
    operator fun invoke() {
        mainNavProvider.navigateTo(MainDestinations.Profile)
        profileNavProvider.navigateTo(ProfileDestinations.Menu)
    }
}