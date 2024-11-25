package com.prasoon.atlysandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.prasoon.atlysandroid.vm.MovieViewModel

@Composable
fun ErrorScreen(vm: MovieViewModel, innerPadding: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Something went wrong")
    }
}