package com.emelyanov.rassvet.modules.main.modules.trainings.domain.utils

import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.Training
import com.emelyanov.rassvet.shared.domain.models.responseModels.TrainingResponse
import com.emelyanov.rassvet.shared.domain.utils.getDateFromString
import javax.inject.Inject


class TrainingMapper
@Inject
constructor(

) {
    fun map(trainingResponse: TrainingResponse) : Training
    = trainingResponse.let {
        Training(
            id = it.id,
            title = it.title,
            sectionId = it.sectionId,
            startDate = getDateFromString(it.startDate),
            durationInMinutes = it.durationInMinutes,
            trainerFullName = it.trainerFullName
        )
    }
}