package com.emelyanov.rassvet.modules.main.modules.trainings.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState



@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun TrainingsTab(

) {
    val pagerState = rememberPagerState(0)

    SolidBackgroundBox {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TrainingsDropdownBar(
                //modifier = Modifier.align(Alignment.TopCenter)
                pagerState = pagerState
            )

            HorizontalPager(
                modifier = Modifier.weight(1f),
                count = 2,
                state = pagerState
            ) { page ->
                when(page) {
                    0 -> ActiveTrainingsPage()
                    1 -> PastTrainingsPage()
                }
            }
        }
    }
}

@Composable
fun ActiveTrainingsPage() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Spacer(Modifier.height(10.dp))
        }

        items(4) {
            TrainingGroup {
                TrainingGroupItem()
                TrainingGroupItem()
                TrainingGroupItem()
            }
        }

        item {
            Spacer(Modifier.height((NAV_BAR_HEIGHT + NAV_BAR_PADDING + 10).dp))
        }
    }
}

@Composable
fun PastTrainingsPage() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Spacer(Modifier.height(10.dp))
        }

        items(16) {
            TrainingShortCard()
        }

        item {
            Spacer(Modifier.height((NAV_BAR_HEIGHT + NAV_BAR_PADDING + 10).dp))
        }
    }
}