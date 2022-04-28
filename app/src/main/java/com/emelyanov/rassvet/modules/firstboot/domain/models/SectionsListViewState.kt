package com.emelyanov.rassvet.modules.firstboot.domain.models

sealed class SectionsListViewState {
    object Loading : SectionsListViewState()
    data class Error(val message: String) : SectionsListViewState()
    data class PresentSections(
        val sections: List<Int>,
        val onSectionClick: (Int) -> Unit
        ) : SectionsListViewState()
}