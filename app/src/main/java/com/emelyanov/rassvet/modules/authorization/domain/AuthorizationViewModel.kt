package com.emelyanov.rassvet.modules.authorization.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.rassvet.navigation.authorization.AuthNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel
@Inject
constructor(
    val authNavController: AuthNavProvider
) : ViewModel() {

}