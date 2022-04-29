package com.emelyanov.rassvet.modules.main.modules.profile.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionsListViewState
import com.emelyanov.rassvet.modules.main.modules.profile.domain.domain.ProfileSectionsViewState
import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.emelyanov.rassvet.navigation.profile.ProfileDestinations
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSectionsViewModel
@Inject
constructor(
    private val _profileNavProvider: ProfileNavProvider
) : ViewModel() {
    private val _profileSectionsViewState: MutableState<ProfileSectionsViewState>
        = mutableStateOf(ProfileSectionsViewState.Loading)
    val profileSectionsViewState: State<ProfileSectionsViewState>
        get() = _profileSectionsViewState

    init {
        fetchSections()
    }

    fun fetchSections(){
        viewModelScope.launch {
            _profileSectionsViewState.value = ProfileSectionsViewState.Loading

            delay(1000)

            _profileSectionsViewState.value = ProfileSectionsViewState.PresentInfo(
                sections = (1..9).toList(),
                onSectionClick = { sectionId ->
                    _profileNavProvider.navigateTo(ProfileDestinations.SubscriptionDetails(sectionId))
                }
            )
        }
    }
}