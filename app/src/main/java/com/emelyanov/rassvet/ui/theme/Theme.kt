package com.emelyanov.rassvet.ui.theme

import android.security.identity.NoAuthenticationKeyAvailableException
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.gestures.OverScrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LocalRassvetColors = staticCompositionLocalOf {
    LightColorPalette
}

private val LocalRassvetTypography = staticCompositionLocalOf{
    RassvetTypographyImpl
}

private val LightColorPalette = RassvetColors(
    layoutBackground = LayoutBackgroundColor,
    surfaceBackground = White,
    toolbarBackground = listOf(CreamyBlue, CreamyPurple),
    layoutGradientBackground = listOf(CreamyBlue, CreamyPurple),
    layoutBackgroundIcons = LayoutBackgroundIconsColor,
    surfaceText = Black,
    toolBarText = White,
    buttonText = White,
    layoutText = Black,
    sectionBackButton = CreamyViolet,
    cardIcons = DarkViolet,
    positiveButton = listOf(CreamyBlue, CreamyPurple),
    negativeButton = listOf(PaleRed, CreamyRed),
    error = Red,
    navbarSelectedItem = CreamyViolet,
    navbarUnselectedItem = Gray,
    dialogBackground = White,
    dialogText = Black,
    input = TransparentGray,
    inputPlaceholder = Gray,
    inputText = White,
    dialogSeparator = TransparentGray,
    linkButton = White,
    tabCircleSelected = White,
    tabCircleUnselected = TransparentGray,
    logoColor = White
)

private val DarkColorPalette = RassvetColors(
    layoutBackground = LayoutBackgroundColor,
    surfaceBackground = White,
    toolbarBackground = listOf(CreamyBlue, CreamyPurple),
    layoutGradientBackground = listOf(CreamyBlue, CreamyPurple),
    layoutBackgroundIcons = LayoutBackgroundIconsColor,
    surfaceText = Black,
    toolBarText = White,
    buttonText = White,
    layoutText = Black,
    sectionBackButton = CreamyViolet,
    cardIcons = DarkViolet,
    positiveButton = listOf(CreamyBlue, CreamyPurple),
    negativeButton = listOf(PaleRed, CreamyRed),
    error = Red,
    navbarSelectedItem = CreamyViolet,
    navbarUnselectedItem = Gray,
    dialogBackground = White,
    dialogText = Black,
    input = TransparentGray,
    inputPlaceholder = Gray,
    inputText = White,
    dialogSeparator = TransparentGray,
    linkButton = White,
    tabCircleSelected = White,
    tabCircleUnselected = TransparentGray,
    logoColor = White
)

@ExperimentalFoundationApi
@Composable
fun RassvetTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = RassvetTypographyImpl

    CompositionLocalProvider(
        LocalRassvetColors provides colors,
        LocalRassvetTypography provides typography,
        LocalOverScrollConfiguration provides null,
        LocalIndication provides rememberRipple(),
        content = content
    )
}

object RassvetTheme{
    val colors: RassvetColors
        @Composable
        get() = LocalRassvetColors.current

    val typography: RassvetTypography
        @Composable
        get() = LocalRassvetTypography.current
}
