package com.emelyanov.rassvet.modules.authorization.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class LoginViewState(
    val email: MutableState<String>,
    val password: MutableState<String>
) {
    class Interaction(
        email: MutableState<String> = mutableStateOf(""),
        password: MutableState<String> = mutableStateOf(""),
        val loginClick: () -> Unit,
        val createAccountClick: () -> Unit
    ) : LoginViewState(
        email = email,
        password = password
    )

    class Loading(
        email: MutableState<String> = mutableStateOf(""),
        password: MutableState<String> = mutableStateOf("")
    ) : LoginViewState(
        email = email,
        password = password
    )
}