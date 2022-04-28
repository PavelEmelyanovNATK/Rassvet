package com.emelyanov.rassvet.navigation.trainings

import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.emelyanov.rassvet.navigation.main.MainDestinations
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject


class TrainingsNavProvider
constructor(

) : ITrainingsNavProvider {
    private val _currentDestination: MutableSharedFlow<TrainingsDestinations> = MutableSharedFlow(extraBufferCapacity = 1)
    override fun getDestinationFlow(): SharedFlow<TrainingsDestinations>
            = _currentDestination

    override fun navigateToList() {
        _currentDestination.tryEmit(TrainingsDestinations.TrainingsList)
    }

    override fun navigateToDetails(id: Int) {
        _currentDestination.tryEmit(TrainingsDestinations.TrainingDetails(id))
    }

    override fun popBack() {
        _currentDestination.tryEmit(TrainingsDestinations.PopBack)
    }
}