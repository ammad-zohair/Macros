package com.ammad.shared.extensions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.slidingLineTransition(pagerState: PagerState, distance: Float) =
    graphicsLayer {
        val scrollPosition = pagerState.currentPage + pagerState.currentPageOffsetFraction
        translationX = scrollPosition * distance
    }

fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 4.dp,
    color: Color = Color.Gray
): Modifier = composed {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration),
        label = "scrollbarAlpha"
    )

    drawWithContent {
        drawContent()

        val layoutInfo = state.layoutInfo
        val items = layoutInfo.visibleItemsInfo
        val totalItemsCount = layoutInfo.totalItemsCount

        if (totalItemsCount == 0 || items.isEmpty()) return@drawWithContent

        val viewportHeight = layoutInfo.viewportSize.height.toFloat()
        val firstVisibleIndex = items.first().index
        val visibleItemsCount = items.size

        val scrollbarHeight = (viewportHeight * visibleItemsCount / totalItemsCount)
            .coerceIn(24f, viewportHeight)
        val scrollbarOffsetY = viewportHeight * firstVisibleIndex / totalItemsCount

        drawRoundRect(
            color = color,
            topLeft = Offset(size.width - width.toPx(), scrollbarOffsetY),
            size = Size(width.toPx(), scrollbarHeight),
            alpha = alpha,
            cornerRadius = CornerRadius(width.toPx() / 2, width.toPx() / 2)
        )
    }
}