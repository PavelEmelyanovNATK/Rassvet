package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.shared.domain.models.DateFieldViewState
import com.emelyanov.rassvet.shared.domain.models.TextFieldViewState
import com.emelyanov.rassvet.shared.domain.utils.formatDate
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import java.util.*

private const val TOP_PADDING = 6f
private const val SPACE_BETWEEN_PLACEHOLDER = 0f
private const val PLACEHOLDER_OFFSET = 30f

@ExperimentalMaterialApi
@Composable
fun GlassDateField(
    modifier: Modifier = Modifier,
    state: DateFieldViewState,
    onValueChange: (Date) -> Unit,
    textStyle: TextStyle = RassvetTheme.typography.inputText
        .copy(color = RassvetTheme.colors.inputText),
    placeholderText: String = "",
    placeholderColor: Color = RassvetTheme.colors.inputPlaceholder,
) {
    var dialogVisibility by remember { mutableStateOf(false) }

    var isInFocus by remember { mutableStateOf(false) }
    val placeholderTriggered = state.date != null || isInFocus

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

    Column(
        modifier = modifier.padding(top = (PLACEHOLDER_OFFSET).dp)
    ){
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(25.dp))
                    .clickable {
                        dialogVisibility = true
                    }
                    .background(RassvetTheme.colors.input)
                    .padding(start = 18.dp, top = TOP_PADDING.dp, bottom = 7.dp, end = 18.dp)
                    .onFocusChanged {
                        isInFocus = it.isFocused
                    },
                text = state.date?.formatDate() ?: "",
                style = textStyle,
                overflow = TextOverflow.Clip,
                maxLines = 1
            )

            BoxWithConstraints {
                Text(
                    modifier = Modifier
                        .padding(start = 18.dp, top = TOP_PADDING.dp, bottom = 7.dp, end = 18.dp)
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
                    maxLines = 1
                )
            }

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp),
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "Calendar icon",
                tint = RassvetTheme.colors.surfaceBackground
            )
        }

        if(state is DateFieldViewState.Error)
            Text(
                modifier = Modifier.align(Alignment.End),
                text = state.error,
                style = RassvetTheme.typography.inputText
                    .copy(
                        color = RassvetTheme.colors.error,
                        textAlign = TextAlign.End
                    )
            )
    }

    if(dialogVisibility)
        Dialog(
            onDismissRequest = { dialogVisibility = false }
        ) {
            DatePickerDialog(
                value = state.date,
                onDateSelected = onValueChange,
                onDismissRequest = { dialogVisibility = false }
            )
        }
}