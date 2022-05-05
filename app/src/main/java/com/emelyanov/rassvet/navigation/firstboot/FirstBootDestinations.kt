package com.emelyanov.rassvet.navigation.firstboot

import com.emelyanov.rassvet.navigation.core.CoreDestinations

sealed class FirstBootDestinations(val route: String) {
    object FirstBootScreen : FirstBootDestinations("${CoreDestinations.FirstBoot.route}/info")
    data class SectionDetails(val id: Int) : FirstBootDestinations("${CoreDestinations.FirstBoot.route}/section-details/$id")
    object SectionDetailsCompRoute : FirstBootDestinations("${CoreDestinations.FirstBoot.route}/section-details/{id}")
    object PopBack : FirstBootDestinations("")
}