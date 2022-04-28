package com.emelyanov.rassvet.navigation.trainings

import com.emelyanov.rassvet.navigation.main.MainDestinations
import kotlinx.coroutines.flow.SharedFlow

interface ITrainingsNavProvider {
    fun getDestinationFlow(): SharedFlow<TrainingsDestinations>
    fun navigateToList()
    fun navigateToDetails(id: Int)
    fun popBack()
}