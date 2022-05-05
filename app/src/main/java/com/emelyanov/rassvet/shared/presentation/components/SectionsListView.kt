package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.shared.domain.models.SectionDetailsViewState
import com.emelyanov.rassvet.shared.domain.models.SectionsListViewState

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SectionsListView(
    modifier: Modifier = Modifier,
    viewState: SectionsListViewState
) {
   when(viewState) {
       is SectionsListViewState.Error -> ErrorView(
           modifier = modifier
               .fillMaxSize()
               .padding(20.dp),
           message = viewState.message
       )
       is SectionsListViewState.Loading -> LoadingView(
           modifier = modifier
       )
       is SectionsListViewState.PresentInfo -> PresentationView(
           modifier = modifier,
           viewState = viewState
       )
   }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun LoadingView(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        cells = GridCells.Adaptive(150.dp,),
        contentPadding = PaddingValues(vertical = 15.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(3) { card ->
            key(card){
                ShimmerSectionCard(
                    title = "Section",
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun PresentationView(
    modifier: Modifier = Modifier,
    viewState: SectionsListViewState.PresentInfo
) {
    LazyVerticalGrid(
        modifier = modifier,
        cells = GridCells.Adaptive(150.dp,),
        contentPadding = PaddingValues(vertical = 15.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(viewState.sections) { card ->
            key(card.id){
                SectionCard(
                    title = card.sectionName,
                    onClick = {
                        viewState.onSectionClick(card.id)
                    }
                )
            }
        }
    }
}