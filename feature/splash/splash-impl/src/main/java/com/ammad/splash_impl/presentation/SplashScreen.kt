package com.ammad.splash_impl.presentation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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

val pages = listOf(
    SplashPage("Page 1", "Description 1", R.drawable.fruits, R.color.blue),
    SplashPage("Page 2", "Description 2", R.drawable.salad, R.color.green),
    SplashPage("Page 3", "Description 3", R.drawable.watermelon, R.color.yellow)
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
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pages[pagerState.currentPage].title,
                    fontSize = 20.sp,
                    color = colorResource(pages[pagerState.currentPage].color),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(100.dp))
                PageIndicator(pagerState = pagerState, pages = pages)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = pages[pagerState.currentPage].description,
                fontSize = 16.sp
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
                    TextButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    ) {
                        Text(
                            text = "Back",
                            color = colorResource(pages[pagerState.currentPage].color)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(100.dp))
                }
                if (pagerState.currentPage != pages.size - 1) {
                    Card(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            },
                        colors = CardDefaults.cardColors(containerColor = colorResource(pages[pagerState.currentPage].color)),
                        shape = CircleShape
                    ) {
                        Column(
                            modifier = Modifier.padding(4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                shape = CircleShape
                            ) {
                                Column(
                                    modifier = Modifier.padding(10.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        modifier = Modifier.size(30.dp),
                                        imageVector = Icons.Rounded.ArrowForward,
                                        contentDescription = null,
                                        tint = colorResource(pages[pagerState.currentPage].color)
                                    )
                                }
                            }
                        }
                    }
                } else {
                    TextButton(
                        onClick = { onIntent(SplashIntent.OnGetStartedClicked) }
                    ) {
                        Text(
                            text = "Get Started!",
                            color = colorResource(pages[pagerState.currentPage].color)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun PageIndicator(
    pagerState: PagerState,
    pages: List<SplashPage>
) {
    Box(contentAlignment = Alignment.CenterStart) {
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
