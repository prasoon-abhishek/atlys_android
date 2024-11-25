package com.prasoon.atlysandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prasoon.atlysandroid.ui.theme.AtlysAndroidTheme
import com.prasoon.atlysandroid.view.ErrorScreen
import com.prasoon.atlysandroid.view.LoadingScreen
import com.prasoon.atlysandroid.view.MovieDetailScreen
import com.prasoon.atlysandroid.view.MovieListScreen
import com.prasoon.atlysandroid.vm.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm by viewModels<MovieViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            vm.onActivityCreated(navController)
            AtlysAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    if (navController.currentDestination?.route == MoviesNavGraph.MovieList.toString()) {
                        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ), title = {
                            Text("Small Top App Bar")
                        })
                    }
                }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = MoviesNavGraph.Loading
                    ) {
                        composable<MoviesNavGraph.MovieList> { MovieListScreen(vm, innerPadding) }
                        composable<MoviesNavGraph.Error> { ErrorScreen(vm, innerPadding) }
                        composable<MoviesNavGraph.Loading> { LoadingScreen(vm, innerPadding) }
                        composable<MoviesNavGraph.MovieDetail> {
                            MovieDetailScreen(
                                vm,
                                innerPadding
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
sealed class MoviesNavGraph() {
    @Serializable
    data object MovieDetail : MoviesNavGraph()

    @Serializable
    data object MovieList : MoviesNavGraph()

    @Serializable
    data class Error(val message: String? = null) : MoviesNavGraph()

    @Serializable
    data object Loading : MoviesNavGraph()
}