package com.emelyanov.rassvet.modules.main.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.main.domain.models.RassvetTabs
import com.emelyanov.rassvet.navigation.main.MainDestinations

const val NAV_BAR_HEIGHT = 60f
@Composable
fun RassvetNavBar(
    modifier: Modifier = Modifier,
    currentDestination: String,
    onTrainingsTabClick: () -> Unit,
    onSubscriptionsTabClick: () -> Unit,
    onProfileTabClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    var trainingsButtonColor = RassvetTheme.colors.navbarUnselectedItem
    var subscriptionsButtonColor = RassvetTheme.colors.navbarUnselectedItem
    var profileButtonColor = RassvetTheme.colors.navbarUnselectedItem

    when(currentDestination) {
        MainDestinations.Trainings.route -> trainingsButtonColor = RassvetTheme.colors.navbarSelectedItem
        MainDestinations.Subscriptions.route -> subscriptionsButtonColor = RassvetTheme.colors.navbarSelectedItem
        MainDestinations.Profile.route -> profileButtonColor = RassvetTheme.colors.navbarSelectedItem
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = 1.dp,
            color = RassvetTheme.colors.surfaceBackground
        ) { }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
                Column(
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onTrainingsTabClick
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dumbel),
                        contentDescription = "Trainings icon",
                        tint = trainingsButtonColor
                    )

                    Text(
                        text = "Тренировки",
                        style = RassvetTheme.typography.navItemCaption
                            .copy(color = trainingsButtonColor)
                    )
                }
                Column(
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onSubscriptionsTabClick
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .size(74.dp, 48.dp)
                            .background(subscriptionsButtonColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(56.dp,36.dp),
                            painter = painterResource(id = R.drawable.ic_rassvet_logo),
                            contentDescription = "Subscriptions button",
                            tint = RassvetTheme.colors.surfaceBackground
                        )
                    }

                    Text(
                        text = "Абонементы",
                        style = RassvetTheme.typography.navItemCaption
                            .copy(color = subscriptionsButtonColor)
                    )
                }
                Column(
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onProfileTabClick
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile icon",
                        tint = profileButtonColor
                    )

                    Text(
                        text = "Профиль",
                        style = RassvetTheme.typography.navItemCaption
                            .copy(color = profileButtonColor)
                    )
                }
        }
    }
}