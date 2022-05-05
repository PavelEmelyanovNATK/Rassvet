package com.emelyanov.rassvet.shared.domain.models

import java.util.*

sealed class DateFieldViewState(
    open val date: Date?
) {
    data class Normal(override val date: Date?) : DateFieldViewState(date) {
        fun toError(error: String)
                = Error(
            date = this.date,
            error = error
        )
    }
    data class Error(
        override val date: Date?,
        val error: String
    ) : DateFieldViewState(date) {
        fun toNormal()
                = Normal(date = this.date)
    }
}