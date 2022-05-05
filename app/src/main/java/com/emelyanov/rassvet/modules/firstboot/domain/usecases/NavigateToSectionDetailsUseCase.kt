package com.emelyanov.rassvet.modules.firstboot.domain.usecases

import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.emelyanov.rassvet.navigation.firstboot.FirstBootNavProvider
import dagger.hilt.InstallIn
import javax.inject.Inject

class NavigateToSectionDetailsUseCase
@Inject
constructor(
    private val firstBootNavProvider: FirstBootNavProvider
) {
    operator fun invoke(id: Int){
        firstBootNavProvider.navigateTo(FirstBootDestinations.SectionDetails(id))
    }
}