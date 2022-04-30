package com.emelyanov.rassvet.modules.main.modules.subscriptions.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionsListViewState
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.shared.domain.models.responseModels.SectionResponse
import com.emelyanov.rassvet.shared.presentation.components.SectionCard

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun AllSubscriptionsPage(
    sectionsListViewState: SectionsListViewState
) {
    when(sectionsListViewState) {
        is SectionsListViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is SectionsListViewState.PresentSections -> {
            SectionsPage(
                sections = sectionsListViewState.sections,
                onSectionClick = sectionsListViewState.onSectionClick
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun SectionsPage(
    sections: List<SectionResponse>,
    onSectionClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp,),
        contentPadding = PaddingValues(start = 15.dp, top = 15.dp, end = 15.dp, bottom = (NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(sections) { card ->
            key(card){
                SectionCard(
                    title = "Card $card",
                    onClick = {
                        onSectionClick(card.id)
                    }
                )
            }
        }
    }
}