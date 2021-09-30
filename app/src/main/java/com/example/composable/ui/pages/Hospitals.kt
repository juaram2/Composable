package com.example.composable.ui.pages

import CloudHospitalApi.models.HospitalItemViewModel
import CloudHospitalApi.models.MediaType
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.composable.ui.components.LoadingBar
import com.example.composable.ui.components.Thumbnail
import com.example.composable.ui.detailPages.HomeDetail
import com.example.composable.viewModel.MainViewModel

@Composable
fun Feature(viewModel: MainViewModel) {
    val hospitalViewModel = viewModel.data.observeAsState().value?.items ?: emptyList()
    val loadingState = viewModel.loading.observeAsState().value
    val navController = rememberNavController()
    val scrollState = rememberScrollState()

    if (loadingState == true) {
        LoadingBar()
    }
    else {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(text = "Top Hospitals & Clinics",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 15.dp))

            hospitalViewModel?.forEach { hospitalItemViewModel ->
                Hospital(navController, hospital = hospitalItemViewModel)
            }
        }
    }
}

@Composable
fun Hospital(navController: NavHostController, hospital: HospitalItemViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        elevation = 10.dp
    ) {
        Column() {
            hospital?.let {
                val media = it.medias
                for (i: Int in 0..media?.size!!.minus(1)) {
                    if (media!![i].order == 0 && media!![i].mediaType == MediaType.photo) {
                        val painter = rememberImagePainter(
                            data = media[i].thumbnailUrl
                        )
                        Thumbnail(painter)
                    }
                }
                Text(text = it.name!!, modifier = Modifier
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp),
                    fontSize = 20.sp)
                Text(text = "${it.location?.state}, ${it.location?.country}" , modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp))

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 10.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)) {
                    Row(modifier = Modifier.align(Alignment.Center)) {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Ask a Question")
                        }
                        Divider(modifier = Modifier
                            .width(1.dp)
                            .height(30.dp),
                        color = Color.White)
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Online Consultation")
                        }
                    }
                }
            }
        }
    }
}