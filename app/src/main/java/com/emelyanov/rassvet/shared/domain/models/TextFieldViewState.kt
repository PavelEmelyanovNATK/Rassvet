package com.emelyanov.rassvet.shared.domain.models

sealed class TextFieldViewState(
    open val text: String
) {

    data class Normal(override val text: String) : TextFieldViewState(text) {
        fun toError(error: String)
        = Error(
            text = this.text,
            error = error
        )
    }
    data class Error(
        override val text: String,
        val error: String
    ) : TextFieldViewState(text) {
        fun toNormal()
        = Normal(text = this.text)
    }
}