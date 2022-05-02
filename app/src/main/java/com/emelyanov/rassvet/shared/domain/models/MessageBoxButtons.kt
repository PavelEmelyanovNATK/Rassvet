package com.emelyanov.rassvet.shared.domain.models

sealed class MessageBoxButtons{
    data class Ok(val onClick: () -> Unit) : MessageBoxButtons()

    data class YesNo(
        val yesClick: () -> Unit,
        val noClick: () -> Unit
    ) : MessageBoxButtons()

    data class OkCancel(
        val okClick: () -> Unit,
        val cancelClick: () -> Unit
    ) : MessageBoxButtons()

    data class YesNoCancel(
        val yesClick: () -> Unit,
        val noClick: () -> Unit,
        val cancelClick: () -> Unit
    ) : MessageBoxButtons()
}