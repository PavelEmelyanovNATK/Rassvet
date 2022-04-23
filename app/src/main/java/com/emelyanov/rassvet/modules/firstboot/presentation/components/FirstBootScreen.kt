package com.emelyanov.rassvet.modules.firstboot.presentation.components

import android.widget.Toast
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.shared.presentation.components.*
import com.emelyanov.rassvet.ui.theme.Black
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.ui.theme.Red
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.abs

private const val QUOTE_WIDTH = 320f
private const val QUOTE_HEIGHT = 100f
private const val LOGO_TITLE_HEIGHT = 32f
private const val LOGO_TITLE_BOTTOM_PADDING = 18f

private const val LOGO_DEFAULT_WIDTH = 185f
private const val LOGO_DEFAULT_HEIGHT = 120f
private const val LOGO_MINIMIZED_WIDTH = 54f
private const val LOGO_MINIMIZED_HEIGHT = 35f

private const val LOGO_TITLE_OFFSET = -(QUOTE_HEIGHT / 2 + LOGO_TITLE_HEIGHT + LOGO_TITLE_BOTTOM_PADDING)
private const val LOGO_DEFAULT_OFFSET_Y = LOGO_TITLE_OFFSET-(LOGO_TITLE_HEIGHT + LOGO_DEFAULT_HEIGHT/2)

private const val MINIMIZED_LOGO_TOP_PADDING = 14f
private const val MINIMIZED_LOGO_START_PADDING = 10f

@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun FirstBootScreen() {
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
                    1 -> FirstBootSectionsPage()
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


            val minimizedLogoOffsetY = -(screenHeight/2 - MINIMIZED_LOGO_TOP_PADDING - LOGO_MINIMIZED_HEIGHT/2)
            val minimizedLogoOffsetX = -(screenWidth/2 - MINIMIZED_LOGO_START_PADDING - LOGO_MINIMIZED_WIDTH/2)

            val logoCurrentOffsetY =
                if(pagerState.currentPage == 0)
                    LOGO_DEFAULT_OFFSET_Y
                else
                    minimizedLogoOffsetY

            val logoCurrentOffsetX =
                if(pagerState.currentPage == 0)
                    0f
                else
                    minimizedLogoOffsetX

            val additionalLogoOffsetY = (minimizedLogoOffsetY - LOGO_DEFAULT_OFFSET_Y) * pagerState.currentPageOffset
            val additionalLogoOffsetX = minimizedLogoOffsetX * pagerState.currentPageOffset

            val logoCurrentWidth =
                if(pagerState.currentPage == 0)
                    LOGO_DEFAULT_WIDTH
                else
                    LOGO_MINIMIZED_WIDTH

            val logoCurrentHeight =
                if(pagerState.currentPage == 0)
                    LOGO_DEFAULT_HEIGHT
                else
                    LOGO_MINIMIZED_HEIGHT

            val additionalLogoHeight = (LOGO_MINIMIZED_HEIGHT - LOGO_DEFAULT_HEIGHT) * pagerState.currentPageOffset
            val additionalLogoWidth = (LOGO_MINIMIZED_WIDTH - LOGO_DEFAULT_WIDTH) * pagerState.currentPageOffset

            Icon(
                modifier = Modifier
                    .size(
                        (logoCurrentWidth + additionalLogoWidth).dp,
                        (logoCurrentHeight + additionalLogoHeight).dp
                    )
                    .align(Alignment.Center)
                    .offset(x = logoCurrentOffsetX.dp, y = logoCurrentOffsetY.dp)
                    .offset(x = additionalLogoOffsetX.dp, y = additionalLogoOffsetY.dp)
                    .clickable(
                        onClick = {
                            if (pagerState.currentPage == 1)
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(0)
                                }
                        },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                painter = painterResource(id = R.drawable.ic_rassvet_logo),
                contentDescription = "Logo",
                tint = RassvetTheme.colors.logoColor
            )
        }
    }
}

private val cardsId = (1..10).toList()

@Composable
fun FirstBootInfoPage(
    onNextClick: () -> Unit = {}
){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = LOGO_TITLE_OFFSET.dp),
            text = "Рассвет",
            style = RassvetTheme.typography.logoText
                .copy(color = RassvetTheme.colors.logoColor)
        )

        Text(
            modifier = Modifier
                .size(QUOTE_WIDTH.dp, QUOTE_HEIGHT.dp)
                .align(Alignment.Center),
            text = "Крупнейший\nспортивный комплекс\nНовосибирска",
            style = RassvetTheme.typography.quoteText
                .copy(
                    color = RassvetTheme.colors.logoColor,
                    textAlign = TextAlign.Center
                )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlassButton(
                text = "Далее",
                onClick = onNextClick
            )

            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.185f)
                    .sizeIn(maxHeight = 135.dp)
            )

        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun FirstBootSectionsPage(){

    val context = LocalContext.current
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

                LazyVerticalGrid(
                    cells = GridCells.Adaptive(150.dp,),
                    contentPadding = PaddingValues(vertical = 15.dp, horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(cardsId) { card ->
                        key(card){
                            SectionCard(
                                title = "Card $card",
                                onClick = {
                                            Toast.makeText(context, "card $card clicked", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
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
                text = "Перейти к авторизации"
            ){ }

            Spacer(Modifier.height(80.dp))
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Preview(widthDp = 300, heightDp = 500)
@Composable
private fun Preview(){
    RassvetTheme {
        FirstBootScreen()
    }
}