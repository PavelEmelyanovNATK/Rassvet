package com.emelyanov.rassvet.modules.firstboot.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionsListViewState
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.GetSectionInfoUseCase
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.NavigateToAuthUseCase
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.PopDetailsBackUseCase
import com.emelyanov.rassvet.navigation.firstboot.FirstBootNavProvider
import com.emelyanov.rassvet.shared.domain.models.SubscriptionDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FirstBootSubscriptionDetailsViewModel
@Inject
constructor(
    private val getSectionInfo: GetSectionInfoUseCase,
    private val popBack: PopDetailsBackUseCase,
    private val navigateToAuth: NavigateToAuthUseCase
) : ViewModel() {
    private val _sectionsListViewState: MutableState<SubscriptionDetailsViewState> = mutableStateOf(
        SubscriptionDetailsViewState.Loading)
    val sectionsListViewState: State<SubscriptionDetailsViewState>
        get() = _sectionsListViewState

    fun fetchSectionInfo(id: Int){
        viewModelScope.launch {
            _sectionsListViewState.value = SubscriptionDetailsViewState.Loading

            try{
                getSectionInfo(id).let {
                    _sectionsListViewState.value = SubscriptionDetailsViewState.PresentInfo.Unauthorized(
                        title = it.sectionName,
                        description = it.description,
                        price = it.price,
                        onAuthClick = navigateToAuth::invoke
                    )
                }
            } catch (ex: Exception) {
                _sectionsListViewState.value = SubscriptionDetailsViewState.Error(
                    message = ex.message ?: "Неописанная ошибка: ${ex::class.java.simpleName}"
                )
            }
        }
    }

    fun backClicked() = popBack()
}