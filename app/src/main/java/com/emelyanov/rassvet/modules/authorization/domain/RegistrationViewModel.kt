package com.emelyanov.rassvet.modules.authorization.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.authorization.domain.models.RegistrationViewState
import com.emelyanov.rassvet.modules.authorization.domain.usecases.*
import com.emelyanov.rassvet.navigation.authorization.AuthDestinations
import com.emelyanov.rassvet.navigation.authorization.AuthNavProvider
import com.emelyanov.rassvet.shared.domain.models.DateFieldViewState
import com.emelyanov.rassvet.shared.domain.models.TextFieldViewState
import com.emelyanov.rassvet.shared.domain.models.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel
@Inject
constructor(
    private val _authNavProvider: AuthNavProvider,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateConfirmPassword: ValidateConfirmPasswordUseCase,
    private val validateRequiredField: ValidateRequiredFieldUseCase,
    private val validateBirthDate: ValidateBirthDateUseCase,
    private val register: RegisterUseCase,
    private val login: LogInUseCase
) : ViewModel() {
    private val _notificationsFlow: MutableSharedFlow<String?> = MutableSharedFlow(replay = 1)
    val notificationsFlow: SharedFlow<String?> = _notificationsFlow

    fun onNotificationProcessed() = _notificationsFlow.tryEmit(null)

    private val _viewState: MutableState<RegistrationViewState>
    = mutableStateOf(
        RegistrationViewState.Interaction(
            backClick = ::backClick,
            registerClick = ::registerClick
        )
    )
    val viewState: State<RegistrationViewState>
        get() = _viewState

    private fun backClick() = _authNavProvider.navigateTo(AuthDestinations.PopBack)

    private fun registerClick() {
        viewModelScope.launch {
            when(val state = _viewState.value) {
                is RegistrationViewState.Interaction -> {
                    _viewState.value = state.toLoading()
                }
            }

            var validationResult = true

            when(val res = validateEmail(viewState.value.email.value.text)){
                is ValidationResult.Failed -> {
                    validationResult = false
                    viewState.value.email.value = when(val state = viewState.value.email.value) {
                        is TextFieldViewState.Normal -> state.toError(res.message)
                        is TextFieldViewState.Error -> state.copy(error = res.message)
                    }
                }
                is ValidationResult.Success -> {
                    val state = viewState.value.email.value
                    if(state is TextFieldViewState.Error)
                        state.toNormal()
                }
            }

            when(val res = validatePassword(viewState.value.password.value.text)){
                is ValidationResult.Failed -> {
                    validationResult = false
                    viewState.value.password.value = when(val state = viewState.value.password.value) {
                        is TextFieldViewState.Normal -> state.toError(res.message)
                        is TextFieldViewState.Error -> state.copy(error = res.message)
                    }
                }
                is ValidationResult.Success -> {
                    val state = viewState.value.password.value
                    if(state is TextFieldViewState.Error)
                        viewState.value.password.value = state.toNormal()
                }
            }

            when(
                val res = validateConfirmPassword(
                    password = viewState.value.password.value.text,
                    confirmPassword = viewState.value.confirmPassword.value.text
                )
            ){
                is ValidationResult.Failed -> {
                    validationResult = false
                    viewState.value.confirmPassword.value = when(val state = viewState.value.confirmPassword.value) {
                        is TextFieldViewState.Normal -> state.toError(res.message)
                        is TextFieldViewState.Error -> state.copy(error = res.message)
                    }
                }
                is ValidationResult.Success -> {
                    val state = viewState.value.confirmPassword.value
                    if(state is TextFieldViewState.Error)
                        viewState.value.confirmPassword.value = state.toNormal()
                }
            }

            when(val res = validateRequiredField(viewState.value.surname.value.text)){
                is ValidationResult.Failed -> {
                    validationResult = false
                    viewState.value.surname.value = when(val state = viewState.value.surname.value) {
                        is TextFieldViewState.Normal -> state.toError(res.message)
                        is TextFieldViewState.Error -> state.copy(error = res.message)
                    }
                }
                is ValidationResult.Success -> {
                    val state = viewState.value.surname.value
                    if(state is TextFieldViewState.Error)
                        viewState.value.surname.value = state.toNormal()
                }
            }

            when(val res = validateRequiredField(viewState.value.name.value.text)){
                is ValidationResult.Failed -> {
                    validationResult = false
                    viewState.value.name.value = when(val state = viewState.value.name.value) {
                        is TextFieldViewState.Normal -> state.toError(res.message)
                        is TextFieldViewState.Error -> state.copy(error = res.message)
                    }
                }
                is ValidationResult.Success -> {
                    val state = viewState.value.name.value
                    if(state is TextFieldViewState.Error)
                        viewState.value.name.value = state.toNormal()
                }
            }

            when(val res = validateBirthDate(viewState.value.birthDate.value.date)){
                is ValidationResult.Failed -> {
                    validationResult = false
                    viewState.value.birthDate.value = when(val state = viewState.value.birthDate.value) {
                        is DateFieldViewState.Normal -> state.toError(res.message)
                        is DateFieldViewState.Error -> state.copy(error = res.message)
                    }
                }
                is ValidationResult.Success -> {
                    val state = viewState.value.birthDate.value
                    if(state is DateFieldViewState.Error)
                        viewState.value.birthDate.value = state.toNormal()
                }
            }

            if(validationResult == true) {
                try{
                    register(
                        email = viewState.value.email.value.text,
                        password = viewState.value.password.value.text,
                        confirmPassword = viewState.value.confirmPassword.value.text,
                        surname = viewState.value.surname.value.text,
                        name = viewState.value.name.value.text,
                        patronymic = viewState.value.patronymic.value.text,
                        birthDate = viewState.value.birthDate.value.date!!
                    )

                    login(
                        email = viewState.value.email.value.text,
                        password = viewState.value.password.value.text
                    )
                } catch (ex: Exception) {
                    _notificationsFlow.tryEmit(ex.message)
                }
            }

            when(val state = _viewState.value) {
                is RegistrationViewState.Loading -> {
                    _viewState.value = state.toInteraction(::registerClick)
                }
            }
        }
    }
}