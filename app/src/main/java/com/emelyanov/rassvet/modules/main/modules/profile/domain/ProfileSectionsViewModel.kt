package com.emelyanov.rassvet.modules.main.modules.profile.domain

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.NavigateToSectionDetailsUseCase
import com.emelyanov.rassvet.modules.main.modules.profile.domain.models.ProfileSectionsViewState
import com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases.GetProfileSectionsUseCase
import com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases.NavigateToProfileSubscriptionDetailsUseCase
import com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases.PopBackUseCase
import com.emelyanov.rassvet.navigation.profile.ProfileDestinations
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import com.emelyanov.rassvet.shared.domain.usecases.NavigateToAllSubscriptionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSectionsViewModel
@Inject
constructor(
    private val getProfileSections: GetProfileSectionsUseCase,
    private val navigateToSectionDetails: NavigateToProfileSubscriptionDetailsUseCase,
    private val navigateToAllSubscriptions: NavigateToAllSubscriptionsUseCase
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

            try{
                getProfileSections().let {
                    if(it.isEmpty())
                        _profileSectionsViewState.value = ProfileSectionsViewState.NoSubscriptions(
                            onSubscriptionsClick = navigateToAllSubscriptions::invoke
                        )
                    else
                        _profileSectionsViewState.value = ProfileSectionsViewState.PresentInfo(
                            sections = it,
                            onSectionClick = { id ->
                                navigateToSectionDetails(id)
                            }
                        )
                }
            } catch (ex: Exception) {
                _profileSectionsViewState.value = ProfileSectionsViewState.Error(ex.message ?: "Неописанная ошибка ${ex::class.java.simpleName}")
            }
        }
    }
}