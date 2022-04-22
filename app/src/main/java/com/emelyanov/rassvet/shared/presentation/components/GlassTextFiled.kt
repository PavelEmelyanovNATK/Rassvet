package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.CreamyPurple
import com.emelyanov.rassvet.ui.theme.RassvetTheme

private const val TOP_PADDING = 6f
private const val SPACE_BETWEEN_PLACEHOLDER = 0f
private const val PLACEHOLDER_OFFSET = 30f

@Composable
fun GlassTextFiled(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = RassvetTheme.typography.inputText
        .copy(color = RassvetTheme.colors.inputText),
    placeholderText: String = "",
    placeholderColor:Color = RassvetTheme.colors.inputPlaceholder,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    cursorBrush: Brush = SolidColor(RassvetTheme.colors.inputText)
    ) {

    var isPassVisible by remember { mutableStateOf(false) }

    val visualTransformation =
        if((keyboardType == KeyboardType.NumberPassword || keyboardType == KeyboardType.Password) && !isPassVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None



    //var placeholderHeight by remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    //val targetOffset = -(TOP_PADDING +SPACE_BETWEEN_PLACEHOLDER + placeholderHeight)

    var isInfocus by remember { mutableStateOf(false) }
    val placeholderTriggered = value != "" || isInfocus

    val transition = updateTransition(targetState = placeholderTriggered, "Placeholder transition")

    val placeholderOffset = transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 150,
                easing = LinearOutSlowInEasing
            )
        } ,
        label = "Offset animation"
    ) {
        if(it)
            -PLACEHOLDER_OFFSET
        else
            0f
    }


    val placeholderColorAnimation = transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = 150,
                easing = LinearOutSlowInEasing
            )
        },
        label = "Color animation"
    ) {
        if(it)
            textStyle.color
        else
            placeholderColor
    }

    Row(
        modifier = modifier.padding(top = (PLACEHOLDER_OFFSET).dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier.weight(1f)
        ){
            BasicTextField(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(25.dp))
                    .background(RassvetTheme.colors.input)
                    .padding(start = 18.dp, top = TOP_PADDING.dp, bottom = 7.dp, end = 18.dp)
                    .onFocusChanged {
                        isInfocus = it.isFocused
                    },
                value = value,
                onValueChange = onValueChange,
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                visualTransformation = visualTransformation,
                cursorBrush = cursorBrush
            )

            Text(
                modifier = Modifier
                    .padding(start = 18.dp, top = TOP_PADDING.dp, bottom = 7.dp, end = 18.dp)
                    //.onGloballyPositioned {
                    //    placeholderHeight = (it.size.height / density.density)
                    //}
                    .offset(y = placeholderOffset.value.dp),
                style = textStyle.copy(
                    color = placeholderColorAnimation.value,
                    fontWeight =
                    if(placeholderTriggered)
                        FontWeight.Bold
                    else
                        FontWeight.Normal
                ),
                text = placeholderText,
                overflow = TextOverflow.Clip,
                maxLines = maxLines
            )
        }

        if(keyboardType == KeyboardType.Password || keyboardType == KeyboardType.NumberPassword)
            IconButton(
                modifier = Modifier
                    .padding(start=12.dp)
                    .size(25.dp, 17.dp),
                onClick = {
                    isPassVisible = !isPassVisible
                }) {
                Icon(
                   painter = painterResource(id =
                   if(isPassVisible)
                       R.drawable.ic_pass_showed
                   else
                       R.drawable.ic_pass_hidden
                   ),
                    contentDescription = "Pass eye button",
                    tint = RassvetTheme.colors.surfaceBackground
                )
            }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview(){
    RassvetTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CreamyPurple)
        ){
            GlassTextFiled(
                value = "qwqwe",
                onValueChange = {},
                placeholderText = "123124"
            )
        }
    }
}
