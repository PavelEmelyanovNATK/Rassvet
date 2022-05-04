package com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases

import com.emelyanov.rassvet.shared.domain.models.Section
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.Training
import javax.inject.Inject


class SortTrainingsBySectionUseCase
@Inject
constructor(

) {
    operator fun invoke(
        trainings: List<Training>,
        sections: List<Section>
    ) : Map<Section, List<Training>> {
        val map = mutableMapOf<Section, MutableList<Training>>()

        trainings.forEach { training ->
            val section = sections.find { s -> training.sectionId == s.id }
                ?: throw Exception("Ошибка поиска секции")

            map[section]?.add(training) ?:
            if(!map.keys.contains(section))
                map[section] = mutableListOf(training)
            else
                map[section]?.add(training)
        }

        return map
    }
}