package com.ammad.shared.extensions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ammad.shared.shake.ShakeController
import kotlin.math.roundToInt

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

fun Modifier.shake(shakeController: ShakeController) = composed {
    shakeController.shakeConfig?.let { shakeConfig ->
        val shake = remember { Animatable(0f) }
        LaunchedEffect(shakeController.shakeConfig) {
            for (i in 0..shakeConfig.iterations) {
                when (i % 2) {
                    0 -> shake.animateTo(1f, spring(stiffness = shakeConfig.intensity))
                    else -> shake.animateTo(-1f, spring(stiffness = shakeConfig.intensity))
                }
            }
            shake.animateTo(0f)
        }

        this
            .rotate(shake.value * shakeConfig.rotate)
            .graphicsLayer {
                rotationX = shake.value * shakeConfig.rotateX
                rotationY = shake.value * shakeConfig.rotateY
            }
            .scale(
                scaleX = 1f + (shake.value * shakeConfig.scaleX),
                scaleY = 1f + (shake.value * shakeConfig.scaleY),
            )
            .offset {
                IntOffset(
                    (shake.value * shakeConfig.translateX).roundToInt(),
                    (shake.value * shakeConfig.translateY).roundToInt(),
                )
            }
    } ?: this
}

@Composable
fun Modifier.thenIf(
    condition: Boolean,
    builder: @Composable Modifier.() -> Modifier
): Modifier {
    return if (condition) this.then(builder()) else this
}