package com.ammad.splash_impl.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ammad.shared.extensions.slidingLineTransition
import com.ammad.splash_impl.R
import com.ammad.splash_impl.presentation.SplashPage

@Composable
fun PageIndicator(
    pagerState: PagerState,
    pages: List<SplashPage>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(colorResource(R.color.light_grey), RoundedCornerShape(10.dp))
                )
            }
        }

        Box(
            Modifier
                .slidingLineTransition(pagerState, distance = 47f)
                .size(10.dp)
                .background(
                    colorResource(pages[pagerState.currentPage].color),
                    RoundedCornerShape(10.dp)
                )
        )
    }
}