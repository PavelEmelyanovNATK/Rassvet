package com.emelyanov.rassvet.modules.firstboot.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionsListViewState
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
    val firstBootNavProvider: FirstBootNavProvider
) : ViewModel() {
    private val _sectionsListViewState: MutableState<SectionsListViewState> = mutableStateOf(SectionsListViewState.Loading)
    val sectionsListViewState: State<SectionsListViewState>
        get() = _sectionsListViewState

    fun fetchSections(){
        viewModelScope.launch {
            _sectionsListViewState.value = SectionsListViewState.Loading

            delay(5000)

            _sectionsListViewState.value = SectionsListViewState.PresentSections(
                sections = (1..9).toList(),
                onSectionClick = { sectionId ->
                    firstBootNavProvider.navigateTo(FirstBootDestinations.SectionDetails(sectionId))
                }
            )
        }
    }
}