package com.emelyanov.rassvet.modules.firstboot.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.shared.domain.models.SectionsListViewState
import com.emelyanov.rassvet.shared.presentation.components.DotTabs
import com.emelyanov.rassvet.shared.presentation.components.GradientBackgroundBox
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

const val QUOTE_WIDTH = 320f
const val QUOTE_HEIGHT = 120f
const val LOGO_TITLE_HEIGHT = 32f
const val LOGO_TITLE_BOTTOM_PADDING = 18f

const val LOGO_DEFAULT_WIDTH = 185f
const val LOGO_DEFAULT_HEIGHT = 120f
const val LOGO_MINIMIZED_WIDTH = 54f
const val LOGO_MINIMIZED_HEIGHT = 35f

const val LOGO_TITLE_OFFSET = -(QUOTE_HEIGHT / 2 + LOGO_TITLE_HEIGHT + LOGO_TITLE_BOTTOM_PADDING)
const val LOGO_DEFAULT_OFFSET_Y = LOGO_TITLE_OFFSET-(LOGO_TITLE_HEIGHT + LOGO_DEFAULT_HEIGHT/2)

const val MINIMIZED_LOGO_TOP_PADDING = 14f
const val MINIMIZED_LOGO_START_PADDING = 10f


const val LOGO_DELTA_HEIGHT = LOGO_MINIMIZED_HEIGHT - LOGO_DEFAULT_HEIGHT
const val LOGO_DELTA_WIDTH = LOGO_MINIMIZED_WIDTH - LOGO_DEFAULT_WIDTH

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun FirstBootContainer(
    sectionsListViewState: SectionsListViewState,
    onAuthClick: () -> Unit,
    onRefresh: () -> Unit
) {
    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()

    GradientBackgroundBox {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val screenHeight = maxHeight.value
            val screenWidth = maxWidth.value
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                count = 2,
                state = pagerState
            ) { page ->
                when(page){
                    0 -> FirstBootInfoPage(
                        onNextClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )
                    1 -> FirstBootSectionsPage(
                        sectionsListViewState = sectionsListViewState,
                        onAuthClick = onAuthClick,
                        onRefresh = onRefresh
                    )
                }
            }


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DotTabs(
                    pagerState = pagerState
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxHeight(0.06f)
                        .sizeIn(maxHeight = 46.dp)
                )

            }


            //val minimizedLogoOffsetY = -(screenHeight/2 - MINIMIZED_LOGO_TOP_PADDING - LOGO_MINIMIZED_HEIGHT/2)
            //val minimizedLogoOffsetX = -(screenWidth/2 - MINIMIZED_LOGO_START_PADDING - LOGO_MINIMIZED_WIDTH/2)
//
            //val logoCurrentOffsetY =
            //    if(pagerState.currentPage == 0)
            //        LOGO_DEFAULT_OFFSET_Y
            //    else
            //        minimizedLogoOffsetY
//
            //val logoCurrentOffsetX =
            //    if(pagerState.currentPage == 0)
            //        0f
            //    else
            //        minimizedLogoOffsetX
//
            //val additionalLogoOffsetY = (minimizedLogoOffsetY - LOGO_DEFAULT_OFFSET_Y) * pagerState.currentPageOffset
            //val additionalLogoOffsetX = minimizedLogoOffsetX * pagerState.currentPageOffset
//
            //val logoCurrentWidth =
            //    if(pagerState.currentPage == 0)
            //        LOGO_DEFAULT_WIDTH
            //    else
            //        LOGO_MINIMIZED_WIDTH
//
            //val logoCurrentHeight =
            //    if(pagerState.currentPage == 0)
            //        LOGO_DEFAULT_HEIGHT
            //    else
            //        LOGO_MINIMIZED_HEIGHT
//
            //val additionalLogoHeight = LOGO_DELTA_HEIGHT * pagerState.currentPageOffset
            //val additionalLogoWidth = LOGO_DELTA_WIDTH * pagerState.currentPageOffset

            //Icon(
            //    modifier = Modifier
            //        //.size(
            //        //    (logoCurrentWidth + additionalLogoWidth).dp,
            //        //    (logoCurrentHeight + additionalLogoHeight).dp
            //        //)
            //        .size(LOGO_DEFAULT_WIDTH.dp, LOGO_DEFAULT_HEIGHT.dp)
            //        .align(Alignment.Center)
            //        //.offset(x = logoCurrentOffsetX.dp, y = logoCurrentOffsetY.dp)
            //        //.offset(x = additionalLogoOffsetX.dp, y = additionalLogoOffsetY.dp)
            //        .offset(y = LOGO_DEFAULT_OFFSET_Y.dp)
            //        .clickable(
            //            onClick = {
            //                if (pagerState.currentPage == 1)
            //                    coroutineScope.launch {
            //                        pagerState.animateScrollToPage(0)
            //                    }
            //            },
            //            interactionSource = remember { MutableInteractionSource() },
            //            indication = null
            //        ),
            //    painter = painterResource(id = R.drawable.ic_rassvet_logo),
            //    contentDescription = "Logo",
            //    tint = RassvetTheme.colors.logoColor
            //)
        }
    }
}