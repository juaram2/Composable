package com.example.composable.ui.detailPages

import CloudHospitalApi.models.AzureSearchServiceAutocompleteModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.navigation.compose.rememberNavController
import com.example.composable.viewModel.AutoCompleteViewModel

@Composable
fun SearchResult(
    onClick: () -> Unit,
    viewModel: AutoCompleteViewModel
) {
    val keyword = viewModel.autoComplete.observeAsState().value?.values ?: emptyList()
    val loadingState = viewModel.loading.observeAsState().value
    val navController = rememberNavController()

    Column {
        keyword.forEach { keyword ->
            KeywordList(keyword = keyword)
        }
    }
}

@Composable
fun KeywordList(
    keyword: String
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        Text(keyword, modifier = Modifier.padding(bottom = 10.dp))
    }
}
