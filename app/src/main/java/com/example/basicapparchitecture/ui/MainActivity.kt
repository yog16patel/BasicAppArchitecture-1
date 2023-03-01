package com.example.simpleappforinterview.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.simpleappforinterview.domain.entities.MovieInfo
import com.example.basicapparchitecture.ui.common.GeneralText
import com.example.basicapparchitecture.ui.common.TitleText
import com.example.basicapparchitecture.ui.theme.BackgroundColor
import com.example.basicapparchitecture.ui.viewmodels.MovieViewModel

class MainActivity : ComponentActivity() {
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = BackgroundColor
            ) {
                MovieListComposable()
            }

        }
    }


    @Composable
    fun MovieListComposable() {

        val movieState = viewModel.movieListFlow.collectAsState()
        val movieList = movieState.value.movieInfoList
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),

            content = {
            items(movieList.size) { index ->
                SingleMovieListItem(movieList[index])
            }
        })

    }

    @Composable
    fun SingleMovieListItem(movieInfo: MovieInfo) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 8.dp)
        ) {
            AsyncImage(
                model = movieInfo.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .height(270.dp)
                    .width(274.dp)
                    .padding(vertical = 8.dp)
            )
            TitleText(title = movieInfo.name, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.height(2.dp))
            GeneralText(title =  movieInfo.category, modifier = Modifier.padding(start = 8.dp))

        }
    }
}