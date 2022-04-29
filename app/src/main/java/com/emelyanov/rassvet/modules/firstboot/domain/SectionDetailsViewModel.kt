package com.emelyanov.rassvet.modules.firstboot.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionDetailsViewState
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionsListViewState
import com.emelyanov.rassvet.navigation.firstboot.FirstBootNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionDetailsViewModel
@Inject
constructor(

) : ViewModel() {
    private val _sectionsListViewState: MutableState<SectionDetailsViewState> = mutableStateOf(
        SectionDetailsViewState.Loading)
    val sectionsListViewState: State<SectionDetailsViewState>
        get() = _sectionsListViewState

    fun fetchSectionInfo(id: Int){
        viewModelScope.launch {
            _sectionsListViewState.value = SectionDetailsViewState.Loading

            delay(2000)

            _sectionsListViewState.value = SectionDetailsViewState.PresentInfo(
                title = "Секция $id",
                description = "",
                price = 999f
            )
        }
    }
}