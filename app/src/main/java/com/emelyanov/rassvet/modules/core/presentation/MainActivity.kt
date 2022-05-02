package com.emelyanov.rassvet.modules.core.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.authorization.presentation.components.LoginScreen
import com.emelyanov.rassvet.modules.authorization.presentation.components.RegistrationScreen
import com.emelyanov.rassvet.modules.core.domain.CoreViewModel
import com.emelyanov.rassvet.modules.firstboot.presentation.components.FirstBootScreen
import com.emelyanov.rassvet.modules.firstboot.presentation.components.SectionDetailsScreen
import com.emelyanov.rassvet.modules.main.presentation.components.MainScreen
import com.emelyanov.rassvet.navigation.core.CoreNavHost
import com.emelyanov.rassvet.ui.theme.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Rassvet)
        setContent {
            RassvetTheme {
                val coreNavController = rememberAnimatedNavController()
                val coreViewModel = hiltViewModel<CoreViewModel>()
                val context = LocalContext.current

                LaunchedEffect(key1 = true) {
                    coreViewModel.notificationsFlow.onEach {
                        it?.let {
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                            coreViewModel.onNotificationProcessed()
                        }
                    }.launchIn(this)
                }
                
                CoreNavHost(navHostController = coreNavController, coreViewModel = coreViewModel)
            }
        }
    }
}