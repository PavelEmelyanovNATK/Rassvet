package com.emelyanov.rassvet.shared.presentation.components

import android.media.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emelyanov.rassvet.ui.theme.RassvetTheme
import com.emelyanov.rassvet.R

@ExperimentalMaterialApi
@Composable
fun SectionCard(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit = {}
) {
    val cornerRadius = 10.dp
    Surface(
        modifier = Modifier,
        color = RassvetTheme.colors.surfaceBackground,
        shape = RoundedCornerShape(cornerRadius),
        elevation = 2.dp,
        onClick = onClick
    ) {
        Column(
            modifier = modifier.height(150.dp)
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://www.moview.jp/wp-content/uploads/2019/04/senkosan1-1.jpg")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.image_placeholder),
                contentDescription = "stringResource(R.string.description)",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 7.dp, horizontal = 12.dp),
                    text = title,
                    style = RassvetTheme.typography.cardSubtitle
                        .copy(color = RassvetTheme.colors.surfaceText)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview
@Composable
private fun Preview(){
    RassvetTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(RassvetTheme.colors.layoutBackground)
        ) {
            SectionCard(
                title = "Test"
            )
        }
    }
}