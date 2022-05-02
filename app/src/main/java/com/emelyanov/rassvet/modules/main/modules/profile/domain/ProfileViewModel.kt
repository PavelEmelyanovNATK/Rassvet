package com.emelyanov.rassvet.modules.main.modules.profile.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.modules.profile.domain.models.ProfileViewState
import com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases.GetUserDataUseCase
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.AuthorizationService
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.internal.aggregatedroot.codegen._com_emelyanov_rassvet_RassvetApp
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    val profileNavProvider: ProfileNavProvider,
    private val authorizationService: IAuthorizationService,
    getUserData: GetUserDataUseCase
) : ViewModel() {

    private var _viewState: MutableState<ProfileViewState>
    val viewState: State<ProfileViewState>
        get() = _viewState

    init {
        getUserData().let {
            _viewState = mutableStateOf(
                ProfileViewState.Authorized(
                    surname = it.surname,
                    name = it.name,
                    patronymic = it.patronymic,
                    birthDate = it.birthDate,
                    registrationDate = it.registrationDate
                )
            )
        }
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            authorizationService.logout()
        }
    }
}