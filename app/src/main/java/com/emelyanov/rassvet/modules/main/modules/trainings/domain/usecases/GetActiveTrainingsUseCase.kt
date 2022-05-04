package com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases

import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.Training
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.utils.TrainingMapper
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.usecases.GetAccessTokenUseCase
import com.emelyanov.rassvet.shared.domain.usecases.GetAuthHeaderUseCase
import com.emelyanov.rassvet.shared.domain.usecases.ProvideAuthedRequestUseCase
import javax.inject.Inject


class GetActiveTrainingsUseCase
@Inject
constructor(
    private val rassvetApi: IRassvetApi,
    private val trainingMapper: TrainingMapper,
    private val provideAuthedRequest: ProvideAuthedRequestUseCase,
    private val getAuthHeader: GetAuthHeaderUseCase
) {
    suspend operator fun invoke() : List<Training>
    = provideAuthedRequest {
        rassvetApi.fetchClientActiveTrainings(getAuthHeader())
    }?.map {
        trainingMapper.map(it)
    } ?: listOf()
}