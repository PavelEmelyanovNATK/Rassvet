package com.emelyanov.rassvet.modules.main.modules.profile.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emelyanov.rassvet.modules.main.modules.profile.domain.models.ProfileSectionsViewState
import com.emelyanov.rassvet.modules.main.presentation.components.LocalNavBarVisibilityState
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_HEIGHT
import com.emelyanov.rassvet.modules.main.presentation.components.NAV_BAR_PADDING
import com.emelyanov.rassvet.shared.presentation.components.BackButtonLeft
import com.emelyanov.rassvet.shared.presentation.components.SolidBackgroundBox
import com.emelyanov.rassvet.ui.theme.Gray
import com.emelyanov.rassvet.ui.theme.RassvetTheme

@Composable
fun ProfileSectionsScreen(
    profileSectionsViewState: ProfileSectionsViewState,
    onBackClick: () -> Unit
) {
    LocalNavBarVisibilityState.current.value = false

    SolidBackgroundBox {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
                color = RassvetTheme.colors.surfaceBackground,
                elevation = 1.dp
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    BackButtonLeft(
                        color = RassvetTheme.colors.sectionBackButton,
                        onClick = onBackClick
                    )

                    Spacer(Modifier.height(15.dp))

                    Text(
                        text = "Мои абонементы",
                        style = RassvetTheme.typography.toolbarTitle
                            .copy(color = RassvetTheme.colors.surfaceText)
                    )
                }
            }

            when(profileSectionsViewState) {
                is ProfileSectionsViewState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ProfileSectionsViewState.PresentInfo -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 15.dp),
                    ) {
                        Spacer(Modifier.height(15.dp))

                        Surface(
                            shape = RoundedCornerShape(15.dp),
                            color = RassvetTheme.colors.surfaceBackground,
                            elevation = 1.dp
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val count = profileSectionsViewState.sections.size

                                profileSectionsViewState.sections.forEachIndexed { i, section ->
                                    item {
                                        key(section) {
                                            Text(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clickable(
                                                        onClick = { profileSectionsViewState.onSectionClick(section) }
                                                    )
                                                    .padding(vertical = 10.dp, horizontal = 15.dp),
                                                text = "Секция $section",
                                                style = RassvetTheme.typography.cardBody1
                                                    .copy(color = RassvetTheme.colors.surfaceText)
                                            )

                                            if(i < count-1)
                                                Divider(
                                                    modifier = Modifier.padding(horizontal = 10.dp),
                                                    color = Gray.copy(alpha = 0.5f),
                                                    thickness = 0.5.dp
                                                )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height((NAV_BAR_HEIGHT + NAV_BAR_PADDING + 15).dp))
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview() {
    RassvetTheme {

    }
}