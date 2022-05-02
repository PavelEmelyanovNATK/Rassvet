package com.emelyanov.rassvet.modules.firstboot.domain

import android.util.Log
import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionsListViewState
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.GetAllSectionsUseCase
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.NavigateToAuthUseCase
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.NavigateToSectionDetailsUseCase
import com.emelyanov.rassvet.navigation.core.CoreNavProvider
import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.emelyanov.rassvet.navigation.firstboot.FirstBootNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstBootViewModel
@Inject
constructor(
    val firstBootNavProvider: FirstBootNavProvider,
    private val getAllSections: GetAllSectionsUseCase,
    private val navigateToAuth: NavigateToAuthUseCase,
    private val navigateToDetails: NavigateToSectionDetailsUseCase
) : ViewModel() {

    private val _sectionsListViewState: MutableState<SectionsListViewState>
    = mutableStateOf(SectionsListViewState.Loading)
    val sectionsListViewState: State<SectionsListViewState>
        get() = _sectionsListViewState

    init {
        reloadSections()
    }

    fun reloadSections(){
        viewModelScope.launch {
            _sectionsListViewState.value = SectionsListViewState.Loading

            try {
                getAllSections().let { sections ->
                    _sectionsListViewState.value = SectionsListViewState.PresentSections(
                        sections = sections,
                        onSectionClick = { sectionId ->
                            navigateToDetails(sectionId)
                        }
                    )
                }
            }
            catch (ex: Exception) {
                _sectionsListViewState.value = SectionsListViewState.Error(ex.message ?: "Неизвестная ошибка")
            }
        }
    }

    fun authClicked() = navigateToAuth()
}