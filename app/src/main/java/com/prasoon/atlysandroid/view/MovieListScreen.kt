package com.prasoon.atlysandroid.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.prasoon.atlysandroid.data.MovieResult
import com.prasoon.atlysandroid.vm.MovieViewModel

@Composable
fun MovieListScreen(vm: MovieViewModel, innerPadding: PaddingValues) {
    val movieList = vm.movieList.collectAsState().value
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(true) }

    Column(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
        CustomSearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                vm.onMovieSearch(text)
            },
            active = active,
            onActiveChange = { active = it },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            enabled = true,
            placeHolder = { Text("Search Movie") }, // to be replaced with string resources
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Movies") },
            trailingIcon = { }
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 8.dp
                ),
            horizontalArrangement = Arrangement.Center,
            columns = GridCells.Fixed(count = 2)
        ) {
            items(movieList.size) { i ->
                MovieCard(movieList[i], vm)
            }
        }
    }
}

@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeHolder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            enabled = enabled,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch(query) }),
            singleLine = true,
            modifier = Modifier
                .height(56.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { onActiveChange(it.isFocused) }
                .semantics {
                    onClick {
                        focusRequester.requestFocus()
                        true
                    }
                },
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(size = 30.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp) // inner padding

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                        ) {
                            leadingIcon?.let { it() }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box {
                                if (query.isEmpty()) {
                                    placeHolder?.let { it() }
                                }
                                innerTextField()
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                        ) {
                            trailingIcon?.let { it() }
                        }
                    }
                }
            }
        )
        LaunchedEffect(active) {
            if (!active) {
                focusManager.clearFocus()
            }
        }
    }
}

@Composable
fun MovieCard(movie: MovieResult, vm: MovieViewModel) {
    Column {
        Box(modifier = Modifier
            .wrapContentSize()
            .clickable { vm.onMovieClicked(movie) }
            .clip(shape = RoundedCornerShape(12.dp))) {
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            AsyncImage(
                alignment = Alignment.Center,
                model = imageUrl,
                contentDescription = "",
                placeholder = rememberVectorPainter(image = Icons.Default.Settings),
                error = rememberVectorPainter(image = Icons.Default.Settings)
            )
        }
        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 12.dp, bottom = 16.dp, start = 4.dp)
        )
    }
}
