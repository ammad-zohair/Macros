package com.ammad.splash_impl.presentation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ammad.splash_impl.R
import com.ammad.splash_impl.presentation.component.BottomSheet
import com.ammad.splash_impl.presentation.component.IntroPager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

val pages = listOf(
    SplashPage(
        "Search Foods Instantly",
        "Find your favourite foods and explore their complete nutritional profile in seconds",
        R.drawable.fruits,
        R.color.blue
    ),
    SplashPage(
        "Track What Matters",
        "Get accurate calorie and macronutrient information to stay on top of your nutritional goals",
        R.drawable.salad,
        R.color.green
    ),
    SplashPage(
        "Fuel your lifestyle",
        "Make smarter food choices with reliable nutrition data at your fingertips",
        R.drawable.watermelon,
        R.color.yellow
    )
)

@Composable
fun SplashScreen(
    paddingValues: PaddingValues,
    state: SplashState,
    onIntent: (SplashIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState { pages.size }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        LaunchedEffect(key1 = pagerState.currentPage) {
            launch {
                delay(3000.milliseconds)
                with(pagerState) {
                    val target = if (currentPage < pages.size - 1) currentPage + 1 else 0
                    animateScrollToPage(
                        page = target, animationSpec = tween(
                            durationMillis = 0, easing = FastOutLinearInEasing
                        )
                    )
                }
            }
        }

        IntroPager(
            pages = pages,
            pagerState = pagerState
        )
        BottomSheet(
            pagerState = pagerState,
            onIntent = onIntent,
            pages = pages,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}