package com.emelyanov.rassvet.modules.main.modules.profile.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases.GetProfileSectionDetailsUseCase
import com.emelyanov.rassvet.modules.main.modules.profile.domain.usecases.PopBackUseCase
import com.emelyanov.rassvet.modules.main.modules.trainings.domain.usecases.GetSectionDetailsUseCase
import com.emelyanov.rassvet.shared.domain.models.SubscriptionDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileSubscriptionDetailsViewModel
@Inject
constructor(
    private val getProfileSectionDetails: GetProfileSectionDetailsUseCase,
    private val popBack: PopBackUseCase
) : ViewModel() {
    private val _viewState: MutableState<SubscriptionDetailsViewState>
    = mutableStateOf(SubscriptionDetailsViewState.Loading)
    val viewState: State<SubscriptionDetailsViewState>
        get() = _viewState

    fun backClick() = popBack()

    fun fetchInfo(id: Int) {
        viewModelScope.launch {
            _viewState.value = SubscriptionDetailsViewState.Loading

            try{
                getProfileSectionDetails(id).let {
                    _viewState.value = SubscriptionDetailsViewState.PresentInfo.Subscribed(
                        title = it.sectionName,
                        description = it.description,
                        price = it.price,
                        onUnsubscribeClick = { }
                    )
                }
            } catch (ex: Exception) {
                _viewState.value = SubscriptionDetailsViewState.Error(
                    message = ex.message ?: "Неописанная ошибка: ${ex::class.java.simpleName}"
                )
            }
        }
    }
}