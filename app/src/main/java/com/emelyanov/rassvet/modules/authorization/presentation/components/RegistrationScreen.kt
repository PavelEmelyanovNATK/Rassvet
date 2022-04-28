package com.emelyanov.rassvet.modules.authorization.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.shared.presentation.components.*
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.ui.theme.Red
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.max

@ExperimentalPagerApi
@Composable
fun RegistrationScreen(
    onBackClick: () -> Unit
) {
    GradientBackgroundBox {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val coroutineScope = rememberCoroutineScope()

            val maxHeight = maxHeight

            val pagerState = rememberPagerState(0)

            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = maxHeight * 0.14f),
                text = "Регистрация",
                style = RassvetTheme.typography.title
                    .copy(color = RassvetTheme.colors.logoColor)
            )

            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
                count = 2
            ) { page ->
                when(page) {
                    0 -> RegistrationInfoPage(
                        maxHeight = maxHeight,
                        onNextClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )
                    1 -> RegistrationPersonalPage(maxHeight = maxHeight)
                }
            }

            BackButtonLeft(
                modifier = Modifier.padding(25.dp),
                color = RassvetTheme.colors.logoColor,
                onClick = {
                    if(pagerState.currentPage == 0) {
                        onBackClick()
                    }
                    else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    }
                }
            )


            BackHandler {
                if(pagerState.currentPage == 0) {
                    onBackClick()
                }
                else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            }

            DotTabs(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = maxHeight * 0.06f),
                pagerState = pagerState
            )
        }

    }
}

@Composable
fun RegistrationInfoPage(
    maxHeight: Dp,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        var email by remember { mutableStateOf("") }

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            value = email,
            onValueChange = {
                email = it
            },
            placeholderText = "Email"
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            value = "asdasasd",
            onValueChange = {},
            placeholderText = "Пароль"
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            value = "Дата регистрации",
            onValueChange = {},
            placeholderText = "Повторите пароль",
            keyboardType = KeyboardType.Password
        )

        Spacer(Modifier.height(24.dp))

        GlassButton(
            text = "Далее",
            onClick = onNextClick
        )

        Spacer(
            Modifier
                .height(maxHeight * 0.185f)
                .sizeIn(maxHeight = 135.dp)
        )
    }
}

@Composable
fun RegistrationPersonalPage(
    maxHeight: Dp
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            value = "asdasasd",
            onValueChange = {},
            placeholderText = "Фамилия"
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            value = "asdasasd",
            onValueChange = {},
            placeholderText = "Имя"
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            value = "asdasasd",
            onValueChange = {},
            placeholderText = "Отчество"
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            value = "Дата регистрации",
            onValueChange = {},
            placeholderText = "Дата регистрации"
        )

        Spacer(Modifier.height(24.dp))

        GlassButton(
            text = "Зарегистрироваться",
            onClick = { /*TODO*/ }
        )

        Spacer(
            Modifier
                .height(maxHeight * 0.185f)
                .sizeIn(maxHeight = 135.dp)
        )
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview(){
    RassvetTheme {
        RegistrationScreen(
            onBackClick = {}
        )
    }
}