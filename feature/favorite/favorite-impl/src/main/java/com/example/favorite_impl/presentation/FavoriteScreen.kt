package com.example.favorite_impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.components.EmptyView
import com.example.favorite_api.domain.model.Favorite
import com.example.favorite_impl.presentation.components.AddFavoriteCard
import com.example.favorite_impl.presentation.components.FavoriteCard
import com.example.favorite_impl.presentation.components.FavoriteHeader

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues,
    state: FavoriteState,
    onIntent: (FavoriteIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    FavoriteDetailScreen(
        favorites = state.favorites,
        favoriteCount = state.favoriteCount,
        onExploreFoodsClick = { onIntent(FavoriteIntent.ExploreFoods) },
        modifier = modifier.padding(paddingValues)
    )
}

@Composable
private fun FavoriteDetailScreen(
    favorites: List<Favorite>,
    favoriteCount: Int,
    onExploreFoodsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (favorites.isEmpty()) EmptyView("No favorites")
        if (favorites.isNotEmpty()) {
            FavoriteHeader(count = favoriteCount)

            favorites.forEach { favorite ->
                FavoriteCard(favoriteItem = favorite)
            }
            AddFavoriteCard(
                onExploreFoodsClick = onExploreFoodsClick,
                modifier = Modifier
            )

        }
        Spacer(Modifier.height(8.dp))
    }
}






