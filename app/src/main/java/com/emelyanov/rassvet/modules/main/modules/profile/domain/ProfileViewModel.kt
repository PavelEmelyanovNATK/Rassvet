package com.emelyanov.rassvet.modules.main.modules.profile.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.AuthorizationService
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    val profileNavProvider: ProfileNavProvider,
    private val authorizationService: IAuthorizationService
) : ViewModel() {

    fun onLogoutClick() {
        viewModelScope.launch {
            authorizationService.logout()
        }
    }
}