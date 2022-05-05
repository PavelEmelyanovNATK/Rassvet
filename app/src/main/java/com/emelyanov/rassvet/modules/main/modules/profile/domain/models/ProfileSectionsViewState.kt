package com.emelyanov.rassvet.modules.main.modules.profile.domain.models

import com.emelyanov.rassvet.shared.domain.models.Section

sealed class ProfileSectionsViewState {
    object Loading : ProfileSectionsViewState()

    data class NoSubscriptions(
        val onSubscriptionsClick: () -> Unit
    ) : ProfileSectionsViewState()

    data class PresentInfo(
        val sections: List<Section>,
        val onSectionClick: (Int) -> Unit
    ) : ProfileSectionsViewState()

    data class Error(
        val message: String
    ) : ProfileSectionsViewState()
}