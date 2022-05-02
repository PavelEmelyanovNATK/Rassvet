package com.emelyanov.rassvet.modules.authorization.presentation.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.modules.authorization.domain.models.LoginViewState
import com.emelyanov.rassvet.shared.domain.models.MessageBoxButtons
import com.emelyanov.rassvet.shared.domain.models.MessageBoxViewState
import com.emelyanov.rassvet.shared.presentation.components.*
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.annotation.meta.When

@ExperimentalMaterialApi
@Composable
fun LoginScreen(
    loginViewState: LoginViewState,
    loginNotificationsFlow: SharedFlow<String?>,
    onNotificationProcessed: () -> Unit,
) {
    val dialogViewState: MutableState<MessageBoxViewState>
    = remember { mutableStateOf(MessageBoxViewState.Hidden) }

    LaunchedEffect(key1 = true) {
        loginNotificationsFlow.onEach {
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
            val maxHeight = maxHeight
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Icon(
                            modifier = Modifier.size(185.dp,120.dp),
                            painter = painterResource(id = R.drawable.ic_rassvet_logo),
                            contentDescription = "Logo",
                            tint = RassvetTheme.colors.logoColor
                        )
                        Text(
                            text = "Рассвет",
                            style = RassvetTheme.typography.logoText
                                .copy(color = RassvetTheme.colors.logoColor)
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    GlassTextFiled(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sizeIn(maxWidth = 400.dp)
                            .padding(horizontal = 45.dp),
                        value = loginViewState.email.value,
                        onValueChange = {
                            loginViewState.email.value = it
                        },
                        placeholderText = "Email",
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(Modifier.height(4.dp))

                    GlassTextFiled(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sizeIn(maxWidth = 400.dp)
                            .padding(horizontal = 45.dp),
                        value = loginViewState.password.value,
                        onValueChange = {
                            loginViewState.password.value = it
                        },
                        placeholderText = "Пароль",
                        keyboardType = KeyboardType.Password
                    )

                    Spacer(Modifier.height(8.dp))

                    LinkButton(
                        text = "Забыли пароль?",
                        onClick = {}
                    )

                    Spacer(Modifier.height(24.dp))

                    when(loginViewState) {
                        is LoginViewState.Interaction -> GlassButton(
                            text = "Войти",
                            onClick = loginViewState.loginClick
                        )

                        is LoginViewState.Loading -> Box {
                            GlassButton(
                                modifier = Modifier.alpha(0f),
                                text = "Войти",
                                onClick = {  }
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

            LinkButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
                text = "Создать аккаунт",
                onClick = {
                    if(loginViewState is LoginViewState.Interaction)
                        loginViewState.createAccountClick()
                }
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
