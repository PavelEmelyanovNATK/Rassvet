package com.emelyanov.rassvet.modules.firstboot.domain.models

sealed class SectionDetailsViewState {
    object Loading : SectionDetailsViewState()
    data class Error(val message: String) : SectionDetailsViewState()
    data class PresentInfo(
        val title: String,
        val description: String,
        val price: Float
    ) : SectionDetailsViewState()
}