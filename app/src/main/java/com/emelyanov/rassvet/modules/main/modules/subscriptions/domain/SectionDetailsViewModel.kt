package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emelyanov.rassvet.modules.firstboot.domain.usecases.GetSectionInfoUseCase
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases.PopBackFromSectionDetailsUseCase
import com.emelyanov.rassvet.shared.domain.models.SectionDetailsViewState
import com.emelyanov.rassvet.shared.domain.usecases.GetProfileSectionDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SectionDetailsViewModel
@Inject
constructor(
    private val getSectionDetails: GetProfileSectionDetailsUseCase,
    private val popBack: PopBackFromSectionDetailsUseCase
) : ViewModel() {
    private val _viewState: MutableState<SectionDetailsViewState>
            = mutableStateOf(SectionDetailsViewState.Loading)
    val viewState: State<SectionDetailsViewState>
        get() = _viewState

    fun backClick() = popBack()

    fun fetchInfo(id: Int) {
        viewModelScope.launch {
            _viewState.value = SectionDetailsViewState.Loading

            try{
                getSectionDetails(id).let {
                    if(it.isSubscribed)
                        _viewState.value = SectionDetailsViewState.PresentInfo.Subscribed(
                            title = it.sectionName,
                            description = it.description,
                            price = it.price,
                            onUnsubscribeClick = { }
                        )
                    else
                        _viewState.value = SectionDetailsViewState.PresentInfo.Unsubscribed(
                            title = it.sectionName,
                            description = it.description,
                            price = it.price,
                            onSubscribeClick = { }
                        )
                }
            } catch (ex: Exception) {
                _viewState.value = SectionDetailsViewState.Error(
                    message = ex.message ?: "Неописанная ошибка: ${ex::class.java.simpleName}"
                )
            }
        }
    }
}