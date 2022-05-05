package com.emelyanov.rassvet.shared.domain.models

sealed class MessageBoxViewState {
    data class Visible(
        val message: String,
        val buttons: MessageBoxButtons
    ) : MessageBoxViewState()

    object Hidden : MessageBoxViewState()
}