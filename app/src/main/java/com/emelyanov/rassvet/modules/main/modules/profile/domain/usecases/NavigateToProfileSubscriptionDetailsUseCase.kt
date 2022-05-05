package com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases

import com.emelyanov.rassvet.navigation.profile.ProfileDestinations
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import javax.inject.Inject


class NavigateToProfileSubscriptionDetailsUseCase
@Inject
constructor(
    private val profileNavProvider: ProfileNavProvider
) {
    operator fun invoke(id: Int) {
        profileNavProvider.navigateTo(ProfileDestinations.SectionDetails(id))
    }
}