package com.emelyanov.rassvet.modules.authorization.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.shared.presentation.components.GlassButton
import com.emelyanov.rassvet.shared.presentation.components.GlassTextFiled
import com.emelyanov.rassvet.shared.presentation.components.GradientBackgroundBox
import com.emelyanov.rassvet.shared.presentation.components.LinkButton
import com.emelyanov.rassvet.R
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun LoginScreen(
    onCreateAccountClick: () -> Unit,
    onLogInClick: () -> Unit
) {
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
                        value = "asdasasd",
                        onValueChange = {},
                        placeholderText = "Email",
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(Modifier.height(4.dp))

                    GlassTextFiled(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sizeIn(maxWidth = 400.dp)
                            .padding(horizontal = 45.dp),
                        value = "asdasasd",
                        onValueChange = {},
                        placeholderText = "Пароль",
                        keyboardType = KeyboardType.Password
                    )

                    Spacer(Modifier.height(8.dp))

                    LinkButton(
                        text = "Забыли пароль?",
                        onClick = {}
                    )

                    Spacer(Modifier.height(24.dp))

                    GlassButton(
                        text = "Войти",
                        onClick = onLogInClick
                    )

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
                onClick = onCreateAccountClick
            )
        }
    }
}
