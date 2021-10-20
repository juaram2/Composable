package com.example.composable.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.composable.ui.components.CustomSearchBar
import com.example.composable.ui.components.NoResult
import com.example.composable.ui.detailPages.SearchResult
import com.example.composable.viewModel.AutoCompleteViewModel

@Composable
fun Search(
    state: SearchState = rememberSearchState(),
    viewModel: AutoCompleteViewModel
) {
    val keywords = viewModel.autoComplete.observeAsState().value?.values ?: emptyList()

    Column {
        CustomSearchBar(
            query = state.query,
            onQueryChange = { state.query = it },
            searchFocused = state.focused,
            onSearchFocusChange = { state.focused = it },
            onClearQuery = { state.query = TextFieldValue("") }
        )

        if (state.focused && state.query.text.isNotEmpty()){
            viewModel.fetchData(state.query.text)
            state.searchResults = viewModel.autoComplete.value?.values
        }

        when (state.searchDisplay) {
            SearchDisplay.Results -> SearchResult({}, keywords)
            SearchDisplay.NoResults -> NoResult(state.query.text)
        }
    }
}

enum class SearchDisplay {
    Results, NoResults
}

@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    searchResults: List<String>? = emptyList()
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
            searchResults = searchResults
        )
    }
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    searchResults: List<String>?
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
    var searchResults by mutableStateOf(searchResults)
    val searchDisplay: SearchDisplay
        get() = when {
            searchResults?.isEmpty() == true -> SearchDisplay.NoResults
            else -> SearchDisplay.Results
        }
}
