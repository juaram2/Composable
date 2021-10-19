package com.example.composable.ui.pages

import CloudHospitalApi.models.DealItemViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.composable.ui.components.LoadingBar
import com.example.composable.ui.components.Thumbnail
import com.example.composable.viewModel.DealViewModel

@Composable
fun Deals(
    onClick: (String) -> Unit,
    viewModel: DealViewModel
) {
    val hospitals = viewModel.data.observeAsState().value?.items ?: emptyList()
    val loadingState = viewModel.loading.observeAsState().value
    val navController = rememberNavController()

    if (loadingState == true) {
        LoadingBar()
    } else {
        DealsList(hospitals, onClick)
    }
}

@Composable
private fun DealsList(
    hospitals: List<DealItemViewModel>,
    onClick: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        Text(text = "Special Deals",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 15.dp))

        hospitals?.forEach { dealItemViewModel ->
            DealItem(onClick, hospital = dealItemViewModel)
        }
    }
}

@Composable
fun DealItem(
    onClick: (String) -> Unit,
    hospital: DealItemViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        elevation = 10.dp
    ) {
        Column() {
            hospital?.let {
                val painter = rememberImagePainter(
                    data = it.photoThumbnail
                )
                Thumbnail(painter)

                Text(text = it.name!!, modifier = Modifier
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp),
                    fontSize = 20.sp)
                Text(text = "${it.hospitalLocationState}, ${it.hospitalLocationState}" , modifier = Modifier
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