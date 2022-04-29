package com.emelyanov.rassvet.navigation.trainings

import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.emelyanov.rassvet.navigation.main.MainDestinations
import com.emelyanov.rassvet.shared.domain.utils.BaseNavProvider
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject


class TrainingsNavProvider(startDestination: TrainingsDestinations) :
    BaseNavProvider<TrainingsDestinations>(startDestination)