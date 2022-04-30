package com.emelyanov.rassvet.modules.firstboot.domain.models

import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse

sealed class SectionsListViewState {
    object Loading : SectionsListViewState()
    data class Error(val message: String) : SectionsListViewState()
    data class PresentSections(
        val sections: List<SectionResponse>,
        val onSectionClick: (Int) -> Unit
        ) : SectionsListViewState()
}