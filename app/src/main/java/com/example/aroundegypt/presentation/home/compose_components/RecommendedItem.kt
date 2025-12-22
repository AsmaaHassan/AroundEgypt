package com.example.aroundegypt.presentation.home.compose_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Visibility
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.aroundegypt.domain.model.Experience

/**
 * Created by AsmaaHassan on 17,December,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
@Composable
fun RecommendedItem(experience: Experience, onClickItem: (String) -> Unit) {
    Column(
        modifier = Modifier
            .width(220.dp)
            .clickable { onClickItem(experience.id) }
    ) {

        Box {
            AsyncImage(
                model = experience.coverPhoto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )

//            if (experience.is360) {
//                Box(
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .background(Color.Black.copy(alpha = 0.6f), CircleShape)
//                        .padding(8.dp)
//                ) {
//                    Text("360", color = Color.White)
//                }
//            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(experience.title, fontWeight = FontWeight.SemiBold)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Visibility, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
            Text(
                text = " ${experience.likesNumber}",
                color = Color.Gray,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(imageVector = if (experience.isLiked) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },  null, tint = Color(0xFFFF6F61))
        }
    }
}
