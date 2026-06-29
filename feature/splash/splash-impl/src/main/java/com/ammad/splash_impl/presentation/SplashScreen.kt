package com.ammad.splash_impl.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ammad.splash_impl.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import com.example.design_system.component.CustomButton

val pages = listOf(
    SplashPage("Search Foods Instantly", "Find your favourite foods and explore their complete nutritional profile in seconds", R.drawable.fruits, R.color.blue),
    SplashPage("Track What Matters", "Get accurate calorie and macronutrient information to stay on top of your nutritional goals", R.drawable.salad, R.color.green),
    SplashPage("Fuel your lifestyle", "Make smarter food choices with reliable nutrition data at your fingertips", R.drawable.watermelon, R.color.yellow)
)

@Composable
fun SplashScreen(
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

@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    onIntent: (SplashIntent) -> Unit,
    pagerState: PagerState,
    pages: List<SplashPage>
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f),
        shape = RoundedCornerShape(topEnd = 50.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pages[pagerState.currentPage].title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(100.dp))
                PageIndicator(
                    pagerState = pagerState,
                    pages = pages,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = pages[pagerState.currentPage].description,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val scope = rememberCoroutineScope()
                if (pagerState.currentPage != 0) {
                    CustomButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            } },
                        icon = Icons.Rounded.ArrowBack
                    )
                } else {
                    Spacer(modifier = Modifier.width(100.dp))
                }
                AnimatedVisibility (
                    visible = pagerState.currentPage != pages.size - 1, enter = fadeIn(animationSpec = tween(100))) {
                    CustomButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        icon = Icons.Rounded.ArrowForward
                    )
                }
                AnimatedVisibility (
                    visible = pagerState.currentPage == pages.size - 1,
                    enter = fadeIn(animationSpec = tween(200)) + slideInHorizontally(),
                    ) {
                    CustomButton(
                        onClick = { onIntent(SplashIntent.OnGetStartedClicked) },
                        buttonText = "Get Started",
                        icon = Icons.Rounded.ArrowForward
                    )
                }
            }
        }
    }
}

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
                .background(colorResource(pages[pagerState.currentPage].color), RoundedCornerShape(10.dp))
        )
    }
}

private fun Modifier.slidingLineTransition(pagerState: PagerState, distance: Float) =
    graphicsLayer {
        val scrollPosition = pagerState.currentPage + pagerState.currentPageOffsetFraction
        translationX = scrollPosition * distance
    }
