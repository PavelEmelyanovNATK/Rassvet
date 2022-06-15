package com.emelyanov.rassvet

import com.emelyanov.rassvet.modules.main.modules.trainings.domain.models.Training
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases.SortTrainingsBySectionUseCase
import com.emelyanov.rassvet.shared.domain.models.Section
import com.emelyanov.rassvet.shared.domain.utils.formatDay
import com.emelyanov.rassvet.shared.domain.utils.formatMonth
import com.emelyanov.rassvet.shared.domain.utils.formatYear
import com.emelyanov.rassvet.shared.domain.utils.getDateFromString
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class TrainingsSortTest {
    val sortTrainings = SortTrainingsBySectionUseCase()
    val sections = listOf(
        Section(
            id = 1,
            title = "Section 1"
        ),
        Section(
            id = 2,
            title = "Section 2"
        ),
        Section(
            id = 3,
            title = "Section 3"
        )
    )

    @Test
    fun `empty trainings input`() {
        val inputList = listOf<Training>()

        val result = sortTrainings(inputList, sections)
        assertEquals(emptyMap<Section, List<Training>>(), result)
    }

    @Test
    fun `default test`() {
        val inputList = listOf(
            Training(
                id = 1,
                title = "Training 1",
                sectionId = 1,
                startDate = Date(),
                durationInMinutes = 0,
                trainerFullName = ""
            ),
            Training(
                id = 2,
                title = "Training 2",
                sectionId = 1,
                startDate = Date(),
                durationInMinutes = 0,
                trainerFullName = ""
            ),
            Training(
                id = 3,
                title = "Training 3",
                sectionId = 2,
                startDate = Date(),
                durationInMinutes = 0,
                trainerFullName = ""
            ),
            Training(
                id = 4,
                title = "Training 4",
                sectionId = 3,
                startDate = Date(),
                durationInMinutes = 0,
                trainerFullName = ""
            )
        )

        val result = sortTrainings(inputList, sections)
        val expected = mapOf(
            sections[0] to listOf(inputList[0], inputList[1]),
            sections[1] to listOf(inputList[2]),
            sections[2] to listOf(inputList[3]),
        )
        assertEquals(expected, result)
    }
}

class DateFormatterTest {
    val date = Date(122, 4, 24)

    @Test
    fun `days test`() {
        val expected = 24
        val result = date.formatDay()

        assertEquals(expected, result)
    }

    @Test
    fun `month test`() {
        val expected = 5
        val result = date.formatMonth()

        assertEquals(expected, result)
    }

    @Test
    fun `year test`() {
        val expected = 2022
        val result = date.formatYear()

        assertEquals(expected, result)
    }
}

