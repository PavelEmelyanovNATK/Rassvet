package com.emelyanov.rassvet.modules.main.modules.profile.domain.models

sealed class ProfileSectionsViewState {
    object Loading : ProfileSectionsViewState()
    data class PresentInfo(
        val sections: List<Int>,
        val onSectionClick: (Int) -> Unit
    ) : ProfileSectionsViewState()
    data class Error(
        val message: String
    ) : ProfileSectionsViewState()
}