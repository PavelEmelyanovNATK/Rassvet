package com.emelyanov.rassvet.modules.main.modules.trainings.domain.utils

import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.TrainingDetails
import com.emelyanov.rassvet.shared.domain.models.responseModels.TrainingDetailsResponse
import com.emelyanov.rassvet.shared.domain.utils.getDateFromString
import javax.inject.Inject


class TrainingDetailsMapper
@Inject
constructor(

) {
    fun map(trainingDetailsResponse: TrainingDetailsResponse) : TrainingDetails
    = trainingDetailsResponse.let {
        TrainingDetails(
            id = it.id,
            title = it.title,
            room = it.room,
            section = it.sectionName,
            description = it.description,
            durationInMinutes = it.durationInMinutes,
            startDate = getDateFromString(it.startDate),
            trainerFullName = it.trainerFullName
        )
    }
}