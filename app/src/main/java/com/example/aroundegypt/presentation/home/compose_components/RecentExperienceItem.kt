package com.example.aroundegypt.presentation.home.compose_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.aroundegypt.R
import com.example.aroundegypt.domain.model.Experience

/**
 * Created by AsmaaHassan on 17,December,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
@Composable
fun RecentExperienceItem(experience: Experience, onClick: (String) -> Unit
) {
    Column (modifier = Modifier
        .clickable { onClick(experience.id) }
    ){
        Box {
            AsyncImage(
                model = experience.coverPhoto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.placeholder), // Fallback image
                placeholder = painterResource(id = R.drawable.placeholder),
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                experience.title,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            Icon(imageVector = if (experience.isLiked) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },    null, tint = Color(0xFFFF6F61))
            Text(" ${experience.likesNumber}")
        }
    }
}
