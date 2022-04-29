package com.emelyanov.rassvet.modules.main.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.domain.models.RassvetTabs
import com.emelyanov.rassvet.navigation.main.MainDestinations
import com.emelyanov.rassvet.navigation.main.MainNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    val mainNavProvider: MainNavProvider
) : ViewModel() {

}