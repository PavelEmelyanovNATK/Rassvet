package com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.usecases

import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.models.Subscription
import com.emelyanov.rassvet.modules.main.modules.subscriptions.domain.utils.SubscriptionMapper
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.usecases.GetAuthHeaderUseCase
import com.emelyanov.rassvet.shared.domain.usecases.ProvideAuthedRequestUseCase
import com.emelyanov.rassvet.shared.domain.utils.SectionMapper
import javax.inject.Inject


class GetClientSubscriptionsUseCase
@Inject
constructor(
    private val rassvetApi: IRassvetApi,
    private val provideAuthedRequest: ProvideAuthedRequestUseCase,
    private val getAuthHeader: GetAuthHeaderUseCase,
    private val subscriptionMapper: SubscriptionMapper
) {
    suspend operator fun invoke() : List<Subscription>
    = provideAuthedRequest {
        rassvetApi.fetchClientSubscriptions(
            authHeader = getAuthHeader()
        )
    }?.map {
        subscriptionMapper.map(it)
    } ?: listOf()
}