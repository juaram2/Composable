package com.example.composable.ui.detailPages

import CloudHospitalApi.models.HospitalItemViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.composable.viewModel.DetailViewModel

@Composable
fun HomeDetail(hospital: HospitalItemViewModel) {
    val viewModel = DetailViewModel()
    viewModel.fetchHospitalData(hospital.slug!!)
    val detailViewModel = viewModel.hospital.observeAsState().value

    detailViewModel?.let {
        Column() {
            Text(text = hospital.name!!)
        }
    }
}