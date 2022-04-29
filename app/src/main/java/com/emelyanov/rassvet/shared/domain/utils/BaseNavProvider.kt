package com.emelyanov.rassvet.shared.domain.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseNavProvider<D>(startDestination: D) : IBaseNavProvider<D> {
    private val _destinationFlow: MutableStateFlow<D> = MutableStateFlow(startDestination)
    override val destinationFlow: StateFlow<D>
        get() = _destinationFlow

    override fun navigateTo(destination: D) {
        _destinationFlow.tryEmit(destination)
    }
}