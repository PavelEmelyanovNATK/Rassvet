package com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.shared.presentation.components.BackButtonLeft
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components.TrainingShortCard
import com.emelyanov.rassvet.modules.main.presentation.components.LocalNavBarVisibilityState
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.navigation.subscriptions.SubscriptionsNavHost
import com.emelyanov.rassvet.shared.presentation.components.SectionCard
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SubscriptionsTab() {
    val subscriptionsNavController = rememberAnimatedNavController()
    
    SubscriptionsNavHost(subscriptionsNavController = subscriptionsNavController)
}

