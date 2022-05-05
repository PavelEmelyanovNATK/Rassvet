package com.emelyanov.rassvet.modules.firstboot.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.GetSectionInfoUseCase
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.NavigateToAuthUseCase
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.PopDetailsBackUseCase
import com.emelyanov.rassvet.shared.domain.models.SectionDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FirstBootSectionDetailsViewModel
@Inject
constructor(
    private val getSectionInfo: GetSectionInfoUseCase,
    private val popBack: PopDetailsBackUseCase,
    private val navigateToAuth: NavigateToAuthUseCase
) : ViewModel() {
    private val _sectionsListViewState: MutableState<SectionDetailsViewState> = mutableStateOf(
        SectionDetailsViewState.Loading)
    val sectionsListViewState: State<SectionDetailsViewState>
        get() = _sectionsListViewState

    fun fetchSectionInfo(id: Int){
        viewModelScope.launch {
            _sectionsListViewState.value = SectionDetailsViewState.Loading

            try{
                getSectionInfo(id).let {
                    _sectionsListViewState.value = SectionDetailsViewState.PresentInfo.Unauthorized(
                        title = it.sectionName,
                        description = it.description,
                        price = it.price,
                        onAuthClick = navigateToAuth::invoke
                    )
                }
            } catch (ex: Exception) {
                _sectionsListViewState.value = SectionDetailsViewState.Error(
                    message = ex.message ?: "Неописанная ошибка: ${ex::class.java.simpleName}"
                )
            }
        }
    }

    fun backClicked() = popBack()
}