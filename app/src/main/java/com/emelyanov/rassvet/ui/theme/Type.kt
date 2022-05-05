package com.emelyanov.rassvet.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.emelyanov.rassvet.R
import org.w3c.dom.Text
import javax.annotation.concurrent.Immutable

val Mulish = FontFamily(
    Font(resId = R.font.mulish_light, weight = FontWeight.Light),
    Font(resId = R.font.mulish_regular, weight = FontWeight.Normal),
    Font(resId = R.font.mulish_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.mulish_bold, weight = FontWeight.Bold)
)

val Raleway = FontFamily(
    Font(resId = R.font.raleway_bold, weight = FontWeight.Bold)
)

@Immutable
data class RassvetTypography(
    val logoText: TextStyle,
    val quoteText: TextStyle,
    val title: TextStyle,
    val inputText: TextStyle,
    val linkButtonText: TextStyle,
    val buttonText: TextStyle,
    val backButtonText: TextStyle,
    val toolbarTitle: TextStyle,
    val cardGroupTitle: TextStyle,
    val cardTitle: TextStyle,
    val cardSubtitle: TextStyle,
    val cardBody2: TextStyle,
    val cardBody1: TextStyle,
    val navItemCaption: TextStyle
)

val RassvetTypographyImpl = RassvetTypography(
    logoText = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),
    quoteText = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    title = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    inputText = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    linkButtonText = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    buttonText = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    backButtonText = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    toolbarTitle = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    cardGroupTitle = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    cardTitle = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    cardSubtitle = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    cardBody1 = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    cardBody2 = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    navItemCaption = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)