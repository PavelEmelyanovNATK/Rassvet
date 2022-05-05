package com.emelyanov.rassvet.shared.domain.models

sealed class SectionDetailsViewState {
    object Loading : SectionDetailsViewState()

    data class Error(val message: String) : SectionDetailsViewState()

    sealed class PresentInfo(
        open val title: String,
        open val description: String,
        open val price: Int
    ) : SectionDetailsViewState() {
        data class Unauthorized(
            override val title: String,
            override val description: String,
            override val price: Int,
            val onAuthClick: () -> Unit
        ) : PresentInfo(
            title,
            description,
            price
        )

        data class Subscribed(
            override val title: String,
            override val description: String,
            override val price: Int,
            val onUnsubscribeClick: () -> Unit
        ) : PresentInfo(
            title,
            description,
            price
        )

        data class Unsubscribed(
            override val title: String,
            override val description: String,
            override val price: Int,
            val onSubscribeClick: () -> Unit
        ) : PresentInfo(
            title,
            description,
            price
        )
    }
}