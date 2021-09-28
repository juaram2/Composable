package com.example.composable.ui.pages

import CloudHospitalApi.models.HospitalItemViewModel
import CloudHospitalApi.models.MarketingType
import CloudHospitalApi.models.MediaType
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.composable.viewModel.MainViewModel

@Composable
fun Feature(viewModel: MainViewModel) {
    val hospitalViewModel = viewModel.data.observeAsState().value?.items ?: emptyList()
    val scrollState = rememberScrollState()
    viewModel.fetchData(MarketingType.both)

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        hospitalViewModel?.forEach { hospitalItemViewModel ->
            Hospital(hospital = hospitalItemViewModel)
        }
    }
}

@Composable
fun Hospital(hospital: HospitalItemViewModel) {
    Column {
        hospital?.let {
            val media = it.medias
            for (i: Int in 0..media?.size!!.minus(1)) {
                if (media!![i].order == 0 && media!![i].mediaType == MediaType.photo) {
                    val painter = rememberImagePainter(
                        data = media[i].thumbnailUrl
                    )
                    when (painter.state) {
                        is ImagePainter.State.Loading -> {
                            CircularProgressIndicator()
                        }
                        is ImagePainter.State.Error -> {
                            Log.d("debug", "Error!!!")
                        }
                        else -> {
                            Log.d("debug", "state: ${painter.state}")
                        }
                    }

                    Image (
                        painter = painter,
                        contentDescription = "Hospital Image",
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RectangleShape)
                    )
                }
            }
            Text(text = it.name!!, modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}