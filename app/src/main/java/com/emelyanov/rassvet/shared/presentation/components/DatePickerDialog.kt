package com.emelyanov.rassvet.shared.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.ListItemPicker
import com.chargemap.compose.numberpicker.NumberPicker
import com.emelyanov.rassvet.shared.domain.utils.*
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import java.util.*

@ExperimentalMaterialApi
@Composable
fun DatePickerDialog(
    value: Date?,
    onDateSelected: (Date) -> Unit,
    onDismissRequest: () -> Unit
) {
    val selDay = remember { mutableStateOf(value?.formatDay() ?: Date().formatDay()) }

    val selMonth = remember { mutableStateOf(value?.formatMonth() ?: Date().formatMonth()) }

    val selYear= remember { mutableStateOf(value?.formatYear() ?: Date().formatYear()) }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .wrapContentSize()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .background(
                    color = RassvetTheme.colors.input
                )
                .height(60.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart)
                    .padding(start = 25.dp),
                text = "Выберите дату",
                style = RassvetTheme.typography.cardBody1
                    .copy(color = RassvetTheme.colors.inputText),
            )
        }

        Column(
            modifier = Modifier.background(
                color = Color.White,
            )
        ){
            Spacer(modifier = Modifier.size(6.dp))

            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            ){
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    NumberPicker(
                        value = selDay.value,
                        onValueChange = {selDay.value = it},
                        range = 1..getDaysInMonth(selMonth.value, selYear.value),
                        textStyle = RassvetTheme.typography.inputText
                            .copy(color = RassvetTheme.colors.surfaceText),
                        dividersColor = Gray
                    )

                    ListItemPicker(
                        value = getMonthName(selMonth.value),
                        onValueChange = {selMonth.value = getMonthIndex(it) },
                        list = getMonthList(),
                        textStyle = RassvetTheme.typography.inputText
                            .copy(color = RassvetTheme.colors.surfaceText),
                        dividersColor = Gray
                    )

                    NumberPicker(
                        value = selYear.value,
                        onValueChange = {selYear.value = it},
                        range = 1900..Calendar.getInstance().time.formatYear(),
                        textStyle = RassvetTheme.typography.inputText
                            .copy(color = RassvetTheme.colors.surfaceText),
                        dividersColor = Gray
                    )
                }
            }

            Spacer(modifier = Modifier.size(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                    )
            ){
                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                    color = Color.Transparent,
                    onClick = onDismissRequest
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 8.dp, end = 5.dp, bottom = 15.dp),
                        text = "Отмена",
                        style = RassvetTheme.typography.cardBody1
                            .copy(
                                color = RassvetTheme.colors.surfaceText,
                                textAlign = TextAlign.Center
                            )
                    )
                }

                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((0.6f).dp)
                        .padding(vertical = 5.dp),
                    color = Gray
                ){}

                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                    color = Color.Transparent,
                    onClick = {
                        onDateSelected(Date(selYear.value - 1900, selMonth.value-1, selDay.value))
                        onDismissRequest()
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 8.dp, end = 20.dp, bottom = 15.dp),
                        text = "Ок",
                        style = RassvetTheme.typography.cardBody1
                            .copy(
                                color = RassvetTheme.colors.surfaceText,
                                textAlign = TextAlign.Center
                            )
                    )
                }
            }
        }
    }
}