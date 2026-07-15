package com.ammad.splash_impl.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ammad.splash_impl.presentation.SplashIntent
import com.ammad.splash_impl.presentation.SplashPage
import com.example.design_system.components.CustomButton
import kotlinx.coroutines.launch

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
                            }
                        },
                        icon = Icons.Rounded.ArrowBack
                    )
                } else {
                    Spacer(modifier = Modifier.width(100.dp))
                }
                AnimatedVisibility(
                    visible = pagerState.currentPage != pages.size - 1,
                    enter = fadeIn(animationSpec = tween(100))
                ) {
                    CustomButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        icon = Icons.Rounded.ArrowForward
                    )
                }
                AnimatedVisibility(
                    visible = pagerState.currentPage == pages.size - 1,
                    enter = fadeIn(animationSpec = tween(200)) + slideInHorizontally(),
                ) {
                    CustomButton(
                        onClick = {
                            onIntent(SplashIntent.OnGetStartedClicked)
                            onIntent(SplashIntent.OnboardingCompleted)
                        },
                        buttonText = "Get Started",
                        icon = Icons.Rounded.ArrowForward
                    )
                }
            }
        }
    }
}