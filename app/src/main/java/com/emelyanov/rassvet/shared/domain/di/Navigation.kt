package com.emelyanov.rassvet.shared.domain.di

import com.emelyanov.rassvet.navigation.authorization.AuthDestinations
import com.emelyanov.rassvet.navigation.authorization.AuthNavProvider
import com.emelyanov.rassvet.navigation.core.CoreDestinations
import com.emelyanov.rassvet.navigation.core.CoreNavProvider
import com.emelyanov.rassvet.navigation.firstboot.FirstBootDestinations
import com.emelyanov.rassvet.navigation.firstboot.FirstBootNavProvider
import com.emelyanov.rassvet.navigation.main.MainDestinations
import com.emelyanov.rassvet.navigation.main.MainNavProvider
import com.emelyanov.rassvet.navigation.profile.ProfileDestinations
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListDestinations
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsListNavProvider
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavProvider
import com.emelyanov.rassvet.navigation.trainings.TrainingsDestinations
import com.emelyanov.rassvet.navigation.trainings.TrainingsNavProvider
import com.emelyanov.rassvet.shared.domain.utils.BaseNavProvider
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
    fun provideCoreNavigation(): CoreNavProvider = CoreNavProvider()

    @Singleton
    @Provides
    fun provideFirstBootNavigation(): FirstBootNavProvider = FirstBootNavProvider()

    @Singleton
    @Provides
    fun provideAuthNavigation(): AuthNavProvider = AuthNavProvider()

    @Singleton
    @Provides
    fun provideMainNavigation(): MainNavProvider = MainNavProvider()

    @Singleton
    @Provides
    fun provideTrainingsNavigation(): TrainingsNavProvider = TrainingsNavProvider()

    @Singleton
    @Provides
    fun provideSubscriptionsNavigation(): SubscriptionsNavProvider = SubscriptionsNavProvider()

    @Singleton
    @Provides
    fun provideSubscriptionsListNavigation(): SubscriptionsListNavProvider = SubscriptionsListNavProvider()

    @Singleton
    @Provides
    fun provideProfileNavigation(): ProfileNavProvider = ProfileNavProvider()
}