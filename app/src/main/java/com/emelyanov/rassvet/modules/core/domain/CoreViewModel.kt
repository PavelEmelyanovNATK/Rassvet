package com.emelyanov.rassvet.modules.core.domain

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.core.domain.usecases.IsFirstBootedUseCase
import com.emelyanov.rassvet.navigation.core.CoreDestinations
import com.emelyanov.rassvet.navigation.core.CoreNavProvider
import com.emelyanov.rassvet.shared.domain.models.AuthorizationState
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel
@Inject
constructor(
    val coreNavProvider: CoreNavProvider,
    authService: IAuthorizationService,
    isFirstBooted: IsFirstBootedUseCase
) : ViewModel() {

    private val _notificationsFlow: MutableSharedFlow<String?> = MutableSharedFlow(replay = 1)
    val notificationsFlow: SharedFlow<String?> = _notificationsFlow

    fun onNotificationProcessed() = _notificationsFlow.tryEmit(null)

    init {
        authService.authorizationState.onEach { state ->
            when(state) {
                is AuthorizationState.Unauthorized -> coreNavProvider.navigateTo(CoreDestinations.Authorization)
                is AuthorizationState.Authorized -> coreNavProvider.navigateTo(CoreDestinations.Main)
            }
        }.launchIn(viewModelScope)

        if(isFirstBooted())
            coreNavProvider.navigateTo(CoreDestinations.FirstBoot)
        else {
            viewModelScope.launch {
                try {
                    authService.authorize()
                } catch (ex: Exception) {
                    Log.d("ViewModel", "" + ex.message + " " + ex.javaClass)
                    _notificationsFlow.tryEmit(ex.message)
                }
            }
        }
    }
}