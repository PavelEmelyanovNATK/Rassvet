package com.emelyanov.rassvet.modules.main.modules.profile.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.rassvet.navigation.profile.IProfileNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    val profileNavProvider: IProfileNavProvider
) : ViewModel(){
}