package com.prasoon.atlysandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.prasoon.atlysandroid.vm.MovieViewModel

@Composable
fun MovieDetailScreen(viewModel: MovieViewModel, innerPadding: PaddingValues) {
    val movieDetail = remember { viewModel.movieDetail.value }
    Scaffold(modifier = Modifier.padding(innerPadding),
        topBar = {
            IconButton(onClick = { viewModel.navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            val imageUrl = "https://image.tmdb.org/t/p/w500${movieDetail?.posterPath}"
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(shape = RoundedCornerShape(12.dp))
                    .align(Alignment.CenterHorizontally),
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Translated description of what the image contains",
                    placeholder = rememberVectorPainter(image = Icons.Default.Settings)
                )
            }
            Text(
                modifier = Modifier
                    .padding(all = 16.dp),
                text = movieDetail?.title ?: "",
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = movieDetail?.overview ?: "",
                fontSize = 16.sp
            )
        }
    }
}