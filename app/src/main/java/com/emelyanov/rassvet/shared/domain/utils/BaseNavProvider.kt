package com.emelyanov.rassvet.shared.domain.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onCompletion

open class BaseNavProvider<D> : IBaseNavProvider<D> {
    private val _destinationFlow: MutableStateFlow<D?> = MutableStateFlow(null)
    override val destinationFlow: StateFlow<D?>
        get() = _destinationFlow

    override fun navigateTo(destination: D) {
        _destinationFlow.tryEmit(destination)
    }

    override fun navigated() {
        _destinationFlow.tryEmit(null)
    }
}

