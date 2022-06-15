package com.emelyanov.rassvet.modules.main.modules.trainings.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.rassvet.navigation.trainings.TrainingsNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingsViewModel
@Inject
constructor(
    val trainingsNavProvider: TrainingsNavProvider
) : ViewModel()