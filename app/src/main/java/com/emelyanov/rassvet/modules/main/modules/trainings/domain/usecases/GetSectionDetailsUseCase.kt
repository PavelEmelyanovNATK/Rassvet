package com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases

import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.TrainingDetails
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.utils.TrainingDetailsMapper
import com.emelyanov.rassvet.shared.domain.models.responseModels.TrainingDetailsResponse
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.usecases.GetAuthHeaderUseCase
import com.emelyanov.rassvet.shared.domain.usecases.ProvideAuthedRequestUseCase
import javax.inject.Inject


class GetSectionDetailsUseCase
@Inject
constructor(
    private val rassvetApi: IRassvetApi,
    private val provideAuthedRequest: ProvideAuthedRequestUseCase,
    private val getAuthHeader: GetAuthHeaderUseCase,
    private val trainingDetailsMapper: TrainingDetailsMapper
) {
    suspend operator fun invoke(id: Int) : TrainingDetails
    = provideAuthedRequest {
        rassvetApi.fetchTrainingDetails(
            getAuthHeader(),
            id
        )
    }!!.let {
        trainingDetailsMapper.map(it)
    }
}