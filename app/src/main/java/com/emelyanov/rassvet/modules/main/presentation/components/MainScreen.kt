package com.emelyanov.rassvet.modules.main.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.main.modules.profile.presentation.components.ProfileTab
import com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation.SubscriptionsTab
import com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components.TrainingDetailsScreen
import com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components.TrainingsTab
import com.google.accompanist.pager.ExperimentalPagerApi

const val NAV_BAR_PADDING = 10f

@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TrainingsTab()
        //SubscriptionsTab()
        //TrainingDetailsScreen()
        //ProfileTab()

        RassvetNavBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(NAV_BAR_PADDING.dp)
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview(){
    RassvetTheme{
        MainScreen()
    }
}