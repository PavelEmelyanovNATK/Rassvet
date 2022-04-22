package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlin.math.abs

@ExperimentalPagerApi
@Composable
fun DotTabs(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    if(pagerState.pageCount > 0)
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ){
            (0 until pagerState.pageCount).forEach { pageIndex ->

                val targetColorAlpha =
                    when {
                        pagerState.currentPage == pageIndex -> 1f - abs(pagerState.currentPageOffset)
                        pagerState.targetPage == pageIndex -> abs(pagerState.currentPageOffset)
                        else -> 0f
                    }

                Box(
                    modifier = Modifier
                        .size(11.dp)
                        .clip(CircleShape)
                        .background(RassvetTheme.colors.tabCircleUnselected)
                        .background(
                            RassvetTheme.colors.tabCircleSelected
                                .copy(alpha = targetColorAlpha)
                        )
                )
            }
        }
}