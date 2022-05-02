package com.emelyanov.rassvet.modules.authorization.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.authorization.domain.models.LoginViewState
import com.emelyanov.rassvet.modules.authorization.domain.models.RegistrationViewState
import com.emelyanov.rassvet.shared.domain.models.MessageBoxButtons
import com.emelyanov.rassvet.shared.domain.models.MessageBoxViewState
import com.emelyanov.rassvet.shared.domain.utils.formatDate
import com.emelyanov.rassvet.shared.presentation.components.*
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.ui.theme.Red
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.math.max

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun RegistrationScreen(
    viewState: RegistrationViewState,
    registrationNotificationFlow: SharedFlow<String?>,
    onNotificationProcessed: () -> Unit
) {
    val dialogViewState: MutableState<MessageBoxViewState>
            = remember { mutableStateOf(MessageBoxViewState.Hidden) }

    LaunchedEffect(key1 = true) {
        registrationNotificationFlow.onEach {
            it?.let {
                dialogViewState.value = MessageBoxViewState.Visible(
                    message = it,
                    buttons = MessageBoxButtons.Ok(
                        onClick = { dialogViewState.value = MessageBoxViewState.Hidden }
                    )
                )
                onNotificationProcessed()
            }
        }.launchIn(this)
    }

    GradientBackgroundBox {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val coroutineScope = rememberCoroutineScope()

            val maxHeight = maxHeight

            val pagerState = rememberPagerState(0)


            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = maxHeight * 0.14f),
                    text = "Регистрация",
                    style = RassvetTheme.typography.title
                        .copy(color = RassvetTheme.colors.logoColor)
                )

                HorizontalPager(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
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
                            },
                            viewState = viewState
                        )
                        1 -> RegistrationPersonalPage(
                            maxHeight = maxHeight,
                            viewState = viewState
                        )
                    }
                }
            }

            BackButtonLeft(
                modifier = Modifier.padding(25.dp),
                color = RassvetTheme.colors.logoColor,
                onClick = {
                    if(pagerState.currentPage == 0) {
                        viewState.backClick()
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
                    viewState.backClick()
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

    val state = dialogViewState.value
    if(state is MessageBoxViewState.Visible)
        Dialog(onDismissRequest = { dialogViewState.value = MessageBoxViewState.Hidden }) {
            GlassMessageBox(
                modifier = Modifier
                    .fillMaxWidth(),
                message = state.message,
                buttons = state.buttons
            )
        }
}

@Composable
fun RegistrationInfoPage(
    maxHeight: Dp,
    onNextClick: () -> Unit,
    viewState: RegistrationViewState
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
            state = viewState.email.value,
            onValueChange = viewState.onEmailChange,
            placeholderText = "Email"
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            state = viewState.password.value,
            onValueChange = viewState.onPasswordChange,
            placeholderText = "Пароль",
            keyboardType = KeyboardType.Password
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            state = viewState.confirmPassword.value,
            onValueChange = viewState.onConfirmPasswordChange,
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

@ExperimentalMaterialApi
@Composable
fun RegistrationPersonalPage(
    maxHeight: Dp,
    viewState: RegistrationViewState
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
            state = viewState.surname.value,
            onValueChange = viewState.onSurnameChange,
            placeholderText = "Фамилия"
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            state = viewState.name.value,
            onValueChange = viewState.onNameChange,
            placeholderText = "Имя"
        )

        Spacer(Modifier.height(4.dp))

        GlassTextFiled(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            state = viewState.patronymic.value,
            onValueChange = viewState.onPatronymicChange,
            placeholderText = "Отчество"
        )

        Spacer(Modifier.height(4.dp))

        GlassDateField(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxWidth = 400.dp)
                .padding(horizontal = 45.dp),
            state = viewState.birthDate.value,
            onValueChange = viewState.onBirthDateChange,
            placeholderText = "Дата рождения"
        )

        Spacer(Modifier.height(24.dp))

        when(viewState) {
            is RegistrationViewState.Interaction -> GlassButton(
                text = "Зарегистрироваться",
                onClick = viewState.registerClick
            )

            is RegistrationViewState.Loading -> Box {
                GlassButton(
                    modifier = Modifier.alpha(0f),
                    text = "Зарегистрироваться",
                    onClick = { }
                )

                ShimmerBox(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .matchParentSize(),
                    cornersColor = RassvetTheme.colors.shimmerCornersColor,
                    centerColor = RassvetTheme.colors.shimmerCenterColor
                )
            }
        }

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
        //RegistrationScreen(
        //    onBackClick = {}
        //)
    }
}