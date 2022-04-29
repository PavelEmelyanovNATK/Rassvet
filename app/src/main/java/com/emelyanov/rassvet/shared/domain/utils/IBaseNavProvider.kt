package com.emelyanov.rassvet.shared.domain.utils

import kotlinx.coroutines.flow.StateFlow

interface IBaseNavProvider<D> {
    val destinationFlow: StateFlow<D>
    fun navigateTo(destination: D)
}