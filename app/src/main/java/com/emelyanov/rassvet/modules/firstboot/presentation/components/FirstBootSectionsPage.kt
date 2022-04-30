package com.emelyanov.rassvet.modules.firstboot.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionsListViewState
import com.emelyanov.rassvet.shared.presentation.components.LinkButton
import com.emelyanov.rassvet.shared.presentation.components.SectionCard
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun FirstBootSectionsPage(
    sectionsListViewState: SectionsListViewState,
    onAuthClick: () -> Unit,
    onRefresh: () -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = sectionsListViewState is SectionsListViewState.Loading
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 7.dp, end = 10.dp),
            text = "Услуги",
            style = RassvetTheme.typography.title
                .copy(color = RassvetTheme.colors.logoColor)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 64.dp, start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(RassvetTheme.colors.layoutBackground)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = onRefresh
                ) {
                    when(sectionsListViewState) {
                        is SectionsListViewState.PresentSections
                        -> PresentationView(viewState = sectionsListViewState)
                        is SectionsListViewState.Loading
                        -> LoadingView()
                        is SectionsListViewState.Error
                        -> ErrorView(message = sectionsListViewState.message)
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .height(15.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                0f to RassvetTheme.colors.layoutBackground,
                                0.3f to RassvetTheme.colors.layoutBackground,
                                1f to Color.Transparent
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(15.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                0f to Color.Transparent,
                                0.6f to RassvetTheme.colors.layoutBackground,
                                1f to RassvetTheme.colors.layoutBackground
                            )
                        )
                )
            }

            Spacer(Modifier.height(14.dp))

            LinkButton(
                text = "Перейти к авторизации",
                onClick = onAuthClick
            )

            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {

    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun PresentationView(
    viewState: SectionsListViewState.PresentSections
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp,),
        contentPadding = PaddingValues(vertical = 15.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(viewState.sections) { card ->
            key(card){
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

@Composable
private fun ErrorView(
    message: String
) {
    com.emelyanov.rassvet.shared.presentation.components.ErrorView(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        message = message
    )
}