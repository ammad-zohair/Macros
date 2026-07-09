package com.example.favorite_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design_system.components.CustomButton
import com.example.design_system.theme.color.OnPrimary
import com.example.design_system.theme.color.OnSurfaceVariant
import com.example.design_system.theme.color.Outline
import com.example.design_system.theme.color.Primary
import com.example.design_system.theme.color.SurfaceContainer

@Composable
fun AddFavoriteCard(
    onExploreFoodsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawRoundRect(
                    color = Outline,
                    style = Stroke(
                        width = 1.5.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f),
                    ),
                    cornerRadius = CornerRadius(24.dp.toPx()),
                )
            }
            .background(color = SurfaceContainer, shape = RoundedCornerShape(24.dp))
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .drawBehind {
                    drawCircle(
                        color = Primary
                    )
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = OnPrimary,
                modifier = Modifier.size(24.dp),
            )
        }

        Text(
            text = "Add more favorites",
            style = MaterialTheme.typography.titleLarge,
            color = Primary,
            textAlign = TextAlign.Center,
        )

        Text(
            text = "Heart items during search to see them here for quick logging.",
            style = MaterialTheme.typography.bodyMedium,
            color = OnSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        CustomButton(
            onClick = onExploreFoodsClick,
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Default.Add,
            buttonText = "Explore Foods"
        )
    }
}