package com.ammad.splash_impl.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ammad.splash_impl.R
import com.ammad.splash_impl.presentation.SplashPage

@Composable
fun PageContent(
    page: SplashPage
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white)),
    ) {
        Image(
            modifier = Modifier.height(800.dp),
            painter = painterResource(page.image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}