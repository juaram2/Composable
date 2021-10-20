package com.example.composable.ui.pages

import CloudHospitalApi.models.ArticleItemViewModel
import CloudHospitalApi.models.MediaType
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.composable.ui.components.LoadingBar
import com.example.composable.ui.components.Thumbnail
import com.example.composable.ui.components.VideoThumbnail
import com.example.composable.viewModel.ArticleViewModel

@Composable
fun Article(
    onClick: (String) -> Unit,
    viewModel: ArticleViewModel
) {
    val articles = viewModel.data.observeAsState().value?.items ?: emptyList()
    val loadingState = viewModel.loading.observeAsState().value
    val navController = rememberNavController()

    if (loadingState == true) {
        LoadingBar()
    } else {
        ArticleList(articles, onClick)
    }
}

@Composable
fun ArticleList(
    articles: List<ArticleItemViewModel>,
    onClick: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        Text(text = "Health Articles & News",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 15.dp))

        articles?.forEach { articleItemViewModel ->
            ArticleItem(onClick, articleItemViewModel)
        }
    }
}

@Composable
fun ArticleItem(
    onClick: (String) -> Unit,
    article: ArticleItemViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        elevation = 10.dp
    ) {
        Column() {
            article?.let {
                if (it.youtubeUrl != null) {
                    VideoThumbnail(it.youtubeUrl!!)
                } else {
                    val painter = rememberImagePainter(
                        it.medias!![0].thumbnailUrl
                    )
                    Thumbnail(painter)
                }
                Text(text = it.title!!, modifier = Modifier
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp, bottom = 10.dp),
                    fontSize = 20.sp)
            }
        }
    }
}