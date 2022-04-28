package com.emelyanov.rassvet.modules.main.modules.profile.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.firstboot.domain.models.SectionDetailsViewState
import com.emelyanov.rassvet.modules.main.presentation.components.LocalNavBarVisibilityState
import com.emelyanov.rassvet.shared.presentation.components.GradientButton
import com.emelyanov.rassvet.shared.presentation.components.HighlightedBackButton
import com.emelyanov.rassvet.ui.theme.Black
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours
import kotlin.math.roundToInt

private const val IMAGE_HEIGHT = 200f
private const val SURFACE_OVERLAP = 20f
private const val DEFAULT_SURFACE_OFFSET = IMAGE_HEIGHT - SURFACE_OVERLAP

private const val SURFACE_CORNER_RADIUS = 15f

@ExperimentalMaterialApi
@Composable
fun SubscriptionDetailsScreen(
    sectionDetailsViewState: SectionDetailsViewState = SectionDetailsViewState.Loading,
    onActionClick: () -> Unit,
    onBackClick: () -> Unit
) {
    LocalNavBarVisibilityState.current.value = false
    val surfaceSwipeState = rememberSwipeableState(initialValue = false)
    val bodyScrollState = rememberScrollState()
    val pxSurfaceOffset = with(LocalDensity.current) { DEFAULT_SURFACE_OFFSET.dp.toPx() }

    val currentCornerRadius = with(LocalDensity.current) {
        if(surfaceSwipeState.offset.value.toDp() < SURFACE_CORNER_RADIUS.dp)
            if(surfaceSwipeState.offset.value < 0)
                0.dp
            else
                surfaceSwipeState.offset.value.toDp()
        else
            SURFACE_CORNER_RADIUS.dp
    }

    val imageShadingMultiplier = 1 - surfaceSwipeState.offset.value / pxSurfaceOffset

    val nestedScrollConnection = NestedScrollConnectionImpl(
        swipeableState = surfaceSwipeState,
        scrollState = bodyScrollState
    )

    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.image_placeholder),
                contentDescription = "stringResource(R.string.description)",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = SolidColor(Black),
                        alpha = (0.5f * imageShadingMultiplier)
                    )
            )

            HighlightedBackButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 15.dp, top = 15.dp),
                onClick = onBackClick
            )
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(0, surfaceSwipeState.offset.value.roundToInt()) }
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = currentCornerRadius,
                        topEnd = currentCornerRadius
                    )
                )
                .background(RassvetTheme.colors.surfaceBackground)
                //.shadow(
                //    elevation = 1.dp,
                //    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                //)
                .swipeable(
                    state = surfaceSwipeState,
                    anchors = mapOf(
                        0f to true,
                        pxSurfaceOffset to false
                    ),
                    orientation = Orientation.Vertical,
                    resistance = null
                )
                .nestedScroll(
                    connection = nestedScrollConnection
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        state = bodyScrollState,
                        flingBehavior = StockFlingBehaviours.getAndroidNativeScroll()
                    )
                    .padding(25.dp)
            ) {
                when(sectionDetailsViewState) {
                    is SectionDetailsViewState.Loading -> {
                        CircularProgressIndicator(Modifier.fillMaxSize())
                    }

                    is SectionDetailsViewState.PresentInfo -> {
                        Text(
                            text = sectionDetailsViewState.title,
                            style = RassvetTheme.typography.title
                                .copy(RassvetTheme.colors.surfaceText)
                        )

                        Text(
                            text = sectionDetailsViewState.description,
                            style = RassvetTheme.typography.cardBody1
                                .copy(RassvetTheme.colors.surfaceText)
                        )

                        Spacer(Modifier.height(100.dp))
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(66.dp, 11.dp)
                    .clip(RoundedCornerShape(50))
                    .border(
                        width = (-2).dp,
                        color = RassvetTheme.colors.surfaceBackground,
                        shape = RoundedCornerShape(50)
                    )
                    .background(
                        RassvetTheme.colors.surfaceBackground
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp, 5.dp)
                        .clip(RoundedCornerShape(50))
                        .border(
                            width = (-2).dp,
                            color = RassvetTheme.colors.surfaceBackground,
                            shape = RoundedCornerShape(50)
                        )
                        .background(
                            RassvetTheme.colors.surfaceText
                                .copy(alpha = 0.65f)
                        )
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .swipeable(
                        state = surfaceSwipeState,
                        anchors = mapOf(
                            0f to true,
                            pxSurfaceOffset to false
                        ),
                        orientation = Orientation.Vertical,
                        resistance = null
                    )
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.End
        ) {
            if(sectionDetailsViewState is SectionDetailsViewState.PresentInfo) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .background(RassvetTheme.colors.surfaceBackground)
                        .padding(start = 8.dp, top = 2.dp, end = 8.dp, bottom = 3.dp)
                ) {
                    Text(
                        text = "${sectionDetailsViewState.price} р/мес",
                        style = RassvetTheme.typography.cardBody1
                            .copy(RassvetTheme.colors.surfaceText)
                    )
                }
            }

            GradientButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Авторизоваться",
                onClick = onActionClick,
                gradient = RassvetTheme.colors.positiveButton)
        }
    }
}

@ExperimentalMaterialApi
private class NestedScrollConnectionImpl<T> (
    val swipeableState: SwipeableState<T>,
    val scrollState: ScrollState
) : NestedScrollConnection {
    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val delta = available.y
        return if (delta < 0) {
            Offset(0f, swipeableState.performDrag(delta))
        } else {
            Offset.Zero
        }
    }
    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val delta = available.y
        return Offset(0f, swipeableState.performDrag(delta))
    }
    override suspend fun onPreFling(available: Velocity): Velocity {
        val delta = available.y
        return if (delta < 0 && scrollState.value == 0) {
            swipeableState.performFling(delta)
            available
        } else {
            Velocity.Zero
        }
    }
    override suspend fun onPostFling(
        consumed: Velocity,
        available: Velocity
    ): Velocity {
        val delta = available.y
        swipeableState.performFling(delta)
        return super.onPostFling(consumed, available)
    }
}