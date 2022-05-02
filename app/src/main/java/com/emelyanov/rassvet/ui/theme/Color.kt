package com.emelyanov.rassvet.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val White = Color(0xffFFFFFF)
val Black = Color(0xff000000)
val CreamyPurple = Color(0xffAF9DCD)
val CreamyBlue = Color(0xff7186E8)
val LayoutBackgroundIconsColor = Color(0xff7B87BF)
val LayoutBackgroundColor = Color(0xffF8F8F8)
val CreamyViolet = Color(0xff8E91DC)
val TransparentGray = Color(0x50E0E0E0)
val Gray = Color(0xffA0A0A0)
val LightGray = Color(0xff959595)
val DarkViolet = Color(0xff575BC1)
val CreamyRed = Color(0xffE87171)
val PaleRed = Color(0xffCD9DA6)
val Red = Color(0xffC34E4E)
val CreamyPink = Color(0xffF1D3D3)

@Immutable
data class RassvetColors(
    val layoutBackground: Color,
    val surfaceBackground: Color,
    val toolbarBackground: List<Color>,
    val layoutGradientBackground: List<Color>,
    val layoutBackgroundIcons: Color,
    val surfaceText: Color,
    val toolBarText: Color,
    val buttonText: Color,
    val layoutText: Color,
    val sectionBackButton: Color,
    val cardIcons: Color,
    val positiveButton: List<Color>,
    val negativeButton: List<Color>,
    val error: Color,
    val navbarSelectedItem: Color,
    val navbarUnselectedItem: Color,
    val dialogBackground: Color,
    val dialogText: Color,
    val input: Color,
    val inputPlaceholder: Color,
    val inputText: Color,
    val dialogSeparator: Color,
    val linkButton: Color,
    val tabCircleSelected: Color,
    val tabCircleUnselected: Color,
    val logoColor: Color,
    val shimmerCornersColor: Color,
    val shimmerCenterColor: Color
)

