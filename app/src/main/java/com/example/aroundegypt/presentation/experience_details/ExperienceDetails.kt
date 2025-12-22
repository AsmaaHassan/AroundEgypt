package com.example.github.presentation.repodetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.aroundegypt.domain.model.Experience
import com.example.github.presentation.repos.HomeViewModel

/**
 * Created by AsmaaHassan on 17,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */

@Composable
fun ExperienceDetails(viewModel: HomeViewModel) {
    val selectedExperience by viewModel.selectedExperience
    selectedExperience.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            it?.coverPhoto?.let { imageRes ->
                HeroImageSection(
                    imageRes = imageRes,
                    views = 0,
                    onExploreClick = { Unit }
                )
            }

            it?.let { data ->
                ContentSection(data) {
                    //on Click Like
                    viewModel.likeExperience(data.id)
                }
            }
        }
    }
}


@Composable
fun HeroImageSection(
    imageRes: String,
    views: Int,
    onExploreClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
    ) {

        AsyncImage(
            model = imageRes,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Explore Button
        Button(
            onClick = onExploreClick,
            modifier = Modifier.align(Alignment.Center),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                "EXPLORE NOW",
                color = Color(0xFFFF6F3D),
                fontWeight = FontWeight.Bold
            )
        }

        // Bottom overlay (views + 360)
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Visibility,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = " $views views",
                color = Color.White,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("360", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ContentSection(data: Experience, onClickLike: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = data.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Location",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            IconButton(onClick = { }) {
                Icon(Icons.Default.Share, contentDescription = null)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.clickable(onClick = onClickLike),
                    imageVector = if (data.isLiked) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    }, contentDescription = null,
                    tint = Color(0xFFFF6F61)
                )
                Text(" ${data.likesNumber}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = data.description,
            color = Color.DarkGray,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}
