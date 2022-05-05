package com.emelyanov.rassvet.modules.authorization.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.emelyanov.rassvet.shared.domain.models.DateFieldViewState
import com.emelyanov.rassvet.shared.domain.models.TextFieldViewState
import java.util.*

sealed class RegistrationViewState(
    val email: MutableState<TextFieldViewState>,
    val onEmailChange: (String) -> Unit,
    val password: MutableState<TextFieldViewState>,
    val onPasswordChange: (String) -> Unit,
    val confirmPassword: MutableState<TextFieldViewState>,
    val onConfirmPasswordChange: (String) -> Unit,
    val surname: MutableState<TextFieldViewState>,
    val onSurnameChange: (String) -> Unit,
    val name: MutableState<TextFieldViewState>,
    val onNameChange: (String) -> Unit,
    val patronymic: MutableState<TextFieldViewState>,
    val onPatronymicChange: (String) -> Unit,
    val birthDate: MutableState<DateFieldViewState>,
    val onBirthDateChange: (Date?) -> Unit,
    val backClick: () -> Unit
) {
    class Interaction(
        email: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onEmailChange: (String) -> Unit = {
            when(val value = email.value) {
                is TextFieldViewState.Normal -> email.value = value.copy(text = it)
                is TextFieldViewState.Error -> email.value = value.copy(text = it)
            }
        },
        password: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onPasswordChange: (String) -> Unit = {
            when(val value = password.value) {
                is TextFieldViewState.Normal -> password.value = value.copy(text = it)
                is TextFieldViewState.Error -> password.value = value.copy(text = it)
            }
        },
        confirmPassword: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onConfirmPasswordChange: (String) -> Unit = {
            when(val value = confirmPassword.value) {
                is TextFieldViewState.Normal -> confirmPassword.value = value.copy(text = it)
                is TextFieldViewState.Error -> confirmPassword.value = value.copy(text = it)
            }
        },
        surname: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onSurnameChange: (String) -> Unit = {
            when(val value = surname.value) {
                is TextFieldViewState.Normal -> surname.value = value.copy(text = it)
                is TextFieldViewState.Error -> surname.value = value.copy(text = it)
            }
        },
        name: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onNameChange: (String) -> Unit = {
            when(val value = name.value) {
                is TextFieldViewState.Normal -> name.value = value.copy(text = it)
                is TextFieldViewState.Error -> name.value = value.copy(text = it)
            }
        },
        patronymic: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onPatronymicChange: (String) -> Unit = {
            when(val value = patronymic.value) {
                is TextFieldViewState.Normal -> patronymic.value = value.copy(text = it)
                is TextFieldViewState.Error -> patronymic.value = value.copy(text = it)
            }
        },
        birthDate: MutableState<DateFieldViewState> = mutableStateOf(DateFieldViewState.Normal(null)),
        onBirthDateChange: (Date?) -> Unit = {
            when(val value = birthDate.value) {
                is DateFieldViewState.Normal -> birthDate.value = value.copy(date = it)
                is DateFieldViewState.Error -> birthDate.value = value.copy(date = it)
            }
        },
        backClick: () -> Unit,
        val registerClick: () -> Unit
    ) : RegistrationViewState(
        email = email,
        onEmailChange = onEmailChange,
        password = password,
        onPasswordChange = onPasswordChange,
        confirmPassword = confirmPassword,
        onConfirmPasswordChange = onConfirmPasswordChange,
        surname = surname,
        onSurnameChange = onSurnameChange,
        name = name,
        onNameChange = onNameChange,
        patronymic = patronymic,
        onPatronymicChange = onPatronymicChange,
        birthDate = birthDate,
        onBirthDateChange = onBirthDateChange,
        backClick = backClick
    ) {
        fun toLoading()
        = Loading(
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            surname = surname,
            name = name,
            patronymic = patronymic,
            birthDate = birthDate,
            backClick = backClick,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onConfirmPasswordChange = onConfirmPasswordChange,
            onSurnameChange = onSurnameChange,
            onNameChange = onNameChange,
            onBirthDateChange = onBirthDateChange
        )
    }

    class Loading(
        email: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onEmailChange: (String) -> Unit = {
            when(val value = email.value) {
                is TextFieldViewState.Normal -> email.value = value.copy(text = it)
                is TextFieldViewState.Error -> email.value = value.copy(text = it)
            }
        },
        password: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onPasswordChange: (String) -> Unit = {
            when(val value = password.value) {
                is TextFieldViewState.Normal -> password.value = value.copy(text = it)
                is TextFieldViewState.Error -> password.value = value.copy(text = it)
            }
        },
        confirmPassword: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onConfirmPasswordChange: (String) -> Unit = {
            when(val value = confirmPassword.value) {
                is TextFieldViewState.Normal -> confirmPassword.value = value.copy(text = it)
                is TextFieldViewState.Error -> confirmPassword.value = value.copy(text = it)
            }
        },
        surname: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onSurnameChange: (String) -> Unit = {
            when(val value = surname.value) {
                is TextFieldViewState.Normal -> surname.value = value.copy(text = it)
                is TextFieldViewState.Error -> surname.value = value.copy(text = it)
            }
        },
        name: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onNameChange: (String) -> Unit = {
            when(val value = name.value) {
                is TextFieldViewState.Normal -> name.value = value.copy(text = it)
                is TextFieldViewState.Error -> name.value = value.copy(text = it)
            }
        },
        patronymic: MutableState<TextFieldViewState> = mutableStateOf(TextFieldViewState.Normal("")),
        onPatronymicChange: (String) -> Unit = {
            when(val value = patronymic.value) {
                is TextFieldViewState.Normal -> patronymic.value = value.copy(text = it)
                is TextFieldViewState.Error -> patronymic.value = value.copy(text = it)
            }
        },
        birthDate: MutableState<DateFieldViewState> = mutableStateOf(DateFieldViewState.Normal(null)),
        onBirthDateChange: (Date?) -> Unit = {
            when(val value = birthDate.value) {
                is DateFieldViewState.Normal -> birthDate.value = value.copy(date = it)
                is DateFieldViewState.Error -> birthDate.value = value.copy(date = it)
            }
        },
        backClick: () -> Unit
    ) : RegistrationViewState(
        email = email,
        onEmailChange = onEmailChange,
        password = password,
        onPasswordChange = onPasswordChange,
        confirmPassword = confirmPassword,
        onConfirmPasswordChange = onConfirmPasswordChange,
        surname = surname,
        onSurnameChange = onSurnameChange,
        name = name,
        onNameChange = onNameChange,
        patronymic = patronymic,
        onPatronymicChange = onPatronymicChange,
        birthDate = birthDate,
        onBirthDateChange = onBirthDateChange,
        backClick = backClick
    ) {
        fun toInteraction(
            registerClick: () -> Unit
        )
        = Interaction(
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            surname = surname,
            name = name,
            patronymic = patronymic,
            birthDate = birthDate,
            backClick = backClick,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onConfirmPasswordChange = onConfirmPasswordChange,
            onSurnameChange = onSurnameChange,
            onNameChange = onNameChange,
            onBirthDateChange = onBirthDateChange,
            registerClick = registerClick
        )
    }
}