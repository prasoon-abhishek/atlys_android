package com.prasoon.atlysandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.prasoon.atlysandroid.R
import com.prasoon.atlysandroid.vm.MovieViewModel

@Composable
fun LoadingScreen(vm: MovieViewModel, innerPadding: PaddingValues) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.loader),
            contentDescription = "loader"
        )
    }
}