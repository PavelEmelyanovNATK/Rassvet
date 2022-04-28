package com.emelyanov.rassvet.shared.domain.di

import com.emelyanov.rassvet.navigation.profile.IProfileNavProvider
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.ISubscriptionsListNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.ISubscriptionsNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavProvider
import com.emelyanov.rassvet.navigation.trainings.ITrainingsNavProvider
import com.emelyanov.rassvet.navigation.trainings.TrainingsNavProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Navigation {
    @Singleton
    @Provides
    fun provideTrainingsNavigation(): ITrainingsNavProvider = TrainingsNavProvider()

    @Singleton
    @Provides
    fun provideSubscriptionsNavigation(): ISubscriptionsNavProvider = SubscriptionsNavProvider()

    @Singleton
    @Provides
    fun provideSubscriptionsListNavigation(): ISubscriptionsListNavProvider = SubscriptionsListNavProvider()

    @Singleton
    @Provides
    fun provideProfileNavigation(): IProfileNavProvider = ProfileNavProvider()
}