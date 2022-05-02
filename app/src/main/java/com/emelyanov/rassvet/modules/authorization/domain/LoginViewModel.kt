package com.emelyanov.rassvet.modules.authorization.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.authorization.domain.models.LoginViewState
import com.emelyanov.rassvet.modules.authorization.domain.usecases.LogInUseCase
import com.emelyanov.rassvet.navigation.authorization.AuthDestinations
import com.emelyanov.rassvet.navigation.authorization.AuthNavProvider
import com.emelyanov.rassvet.shared.domain.models.requestModels.LogInRequest
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val _authNavProvider: AuthNavProvider,
    private val logIn: LogInUseCase
) : ViewModel() {
    private val _notificationsFlow: MutableSharedFlow<String?> = MutableSharedFlow(replay = 1)
    val notificationsFlow: SharedFlow<String?> = _notificationsFlow

    fun onNotificationProcessed() = _notificationsFlow.tryEmit(null)

    private val _viewState: MutableState<LoginViewState>
    = mutableStateOf(
        LoginViewState.Interaction(
            loginClick = ::login,
            createAccountClick = ::createAccountClick
        )
    )

    val viewState: State<LoginViewState>
        get() = _viewState

    private fun login() {
        viewModelScope.launch {
            _viewState.value = LoginViewState.Loading(
                email = _viewState.value.email,
                password = _viewState.value.password
            )

            try{
                logIn(
                    email = viewState.value.email.value,
                    password = viewState.value.password.value
                )
            } catch(ex: Exception) {
                _notificationsFlow.tryEmit(ex.message)

                _viewState.value = LoginViewState.Interaction(
                    email = _viewState.value.email,
                    loginClick = ::login,
                    createAccountClick = ::createAccountClick
                )
            }
        }
    }

    private fun createAccountClick() = _authNavProvider.navigateTo(AuthDestinations.Registration)
}