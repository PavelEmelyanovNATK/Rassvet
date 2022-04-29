package com.emelyanov.rassvet.modules.main.modules.profile.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.rassvet.navigation.profile.ProfileNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    val profileNavProvider: ProfileNavProvider
) : ViewModel(){
}