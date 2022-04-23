package com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation

import android.widget.Toast
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
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
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.shared.presentation.components.SectionCard
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours

private const val TOOL_BAR_HEIGHT = 50f

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SubscriptionsTab() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = RassvetTheme.colors.toolbarBackground,
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOOL_BAR_HEIGHT.dp)
    ) {
        BackButtonLeft(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 25.dp),
            color = RassvetTheme.colors.logoColor
        )

        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "Абонементы",
            style = RassvetTheme.typography.toolbarTitle
                .copy(color = RassvetTheme.colors.logoColor)
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 25.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_sub),
                contentDescription = "Add subscription button",
                tint = RassvetTheme.colors.logoColor
            )
        }
    }

    SolidBackgroundBox(
        modifier = Modifier
            .padding(top = TOOL_BAR_HEIGHT.dp)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
    ) {
        SubscriptionsPage()
    }
}

@Composable
fun OneSubscriptionPage() {
    Column(
        modifier = Modifier
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        SubscriptionDetailCard()

        Text(
            text = "Дата оформления: 10.12.2021",
            style = RassvetTheme.typography.cardBody2
                .copy(color = RassvetTheme.colors.surfaceText)
        )

        Text(
            text = "Действительна до 10.12.2022",
            style = RassvetTheme.typography.cardBody2
                .copy(color = RassvetTheme.colors.surfaceText)
        )
    }
}

private val cardsId = (1..10).toList()

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun SectionsPage() {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp,),
        contentPadding = PaddingValues(start = 15.dp, top = 15.dp, end = 15.dp, bottom = (NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(cardsId) { card ->
            key(card){
                SectionCard(
                    title = "Card $card"
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun SubscriptionsPage() {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp,),
        contentPadding = PaddingValues(start = 15.dp, top = 15.dp, end = 15.dp, bottom = (NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(cardsId) { card ->
            key(card){
                SubscriptionShortCard()
            }
        }
    }
}