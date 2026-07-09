package com.ammad.splash_impl.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ammad.splash_impl.presentation.SplashPage

@Composable
fun IntroPager(
    pages: List<SplashPage>,
    pagerState: PagerState
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { pageIndex ->
        PageContent(page = pages[pageIndex])
    }
}