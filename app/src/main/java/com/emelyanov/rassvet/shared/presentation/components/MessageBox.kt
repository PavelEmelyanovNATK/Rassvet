package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emelyanov.rassvet.shared.domain.models.MessageBoxButtons
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme



val DividerColor = Color(0x66FFFFFF)

private const val BUTTONS_TOP_PADDING = 6f
private const val BUTTONS_BOTTOM_PADDING = 0f//BUTTONS_TOP_PADDING + 1f

@ExperimentalMaterialApi
@Composable
fun GlassMessageBox(
    modifier: Modifier = Modifier.width(IntrinsicSize.Min),
    message: String,
    buttons: MessageBoxButtons
){
    val cornerRadius = 15.dp

    Surface(
        modifier = modifier,
        color = RassvetTheme.colors.surfaceBackground,
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 5.dp),
                text = message,
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 20.dp)
                    .background(Gray)
            )

            when(buttons){
                is MessageBoxButtons.Ok -> MessageBoxButtonOk(buttons.onClick)
                is MessageBoxButtons.YesNo -> MessageBoxButtonYesNo(buttons.yesClick, buttons.noClick)
                is MessageBoxButtons.OkCancel -> MessageBoxButtonOkCancel(buttons.okClick, buttons.cancelClick)
                is MessageBoxButtons.YesNoCancel -> MessageBoxButtonYesNoCancel(buttons.yesClick, buttons.noClick, buttons.cancelClick)
            }

        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MessageBoxButtonOk(onClick: () -> Unit){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        color = Color.Transparent,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, top = BUTTONS_TOP_PADDING.dp, end = 5.dp, bottom = BUTTONS_BOTTOM_PADDING.dp),
            text = "Ок",
            style = RassvetTheme.typography.cardBody1
                .copy(
                    color = RassvetTheme.colors.surfaceText,
                    textAlign = TextAlign.Center
                )
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun MessageBoxButtonYesNo(yesClick: () -> Unit, noClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)

    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Transparent,
            onClick = yesClick) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = BUTTONS_TOP_PADDING.dp, end = 5.dp, bottom = BUTTONS_BOTTOM_PADDING.dp),
                text = "Да",
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )
        }

        VerticalDivider()

        Surface(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Transparent,
            onClick = noClick){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = BUTTONS_TOP_PADDING.dp, end = 20.dp, bottom = BUTTONS_BOTTOM_PADDING.dp),
                text = "Нет",
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MessageBoxButtonOkCancel(okClick: () -> Unit, cancelClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Transparent,
            onClick = okClick) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = BUTTONS_TOP_PADDING.dp, end = 5.dp, bottom = BUTTONS_BOTTOM_PADDING.dp),
                text = "Ок",
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )
        }

        VerticalDivider()

        Surface(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Transparent,
            onClick = cancelClick) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = BUTTONS_TOP_PADDING.dp, end = 20.dp, bottom = BUTTONS_BOTTOM_PADDING.dp),
                text = "Отмена",
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MessageBoxButtonYesNoCancel(yesClick: () -> Unit, noClick: () -> Unit, cancelClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Transparent,
            onClick = yesClick) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = BUTTONS_TOP_PADDING.dp, end = 5.dp, bottom = BUTTONS_BOTTOM_PADDING.dp),
                text = "Да",
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )
        }

        VerticalDivider()

        Surface(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Transparent,
            onClick = noClick) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 15.dp),
                text = "Нет",
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )
        }

        VerticalDivider()

        Surface(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Transparent,
            onClick = cancelClick) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = BUTTONS_TOP_PADDING.dp, end = 20.dp, bottom = BUTTONS_BOTTOM_PADDING.dp),
                text = "Отмена",
                style = RassvetTheme.typography.cardBody1
                    .copy(
                        color = RassvetTheme.colors.surfaceText,
                        textAlign = TextAlign.Center
                    )
            )
        }
    }
}

@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width((0.6f).dp)
            .padding(top = 10.dp, bottom = 10.dp)
            .background(Gray)
    )
}