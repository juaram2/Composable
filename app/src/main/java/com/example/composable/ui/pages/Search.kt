package com.example.composable.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import com.example.composable.ui.components.CustomSearchBar

@Composable
fun Search(
    state: SearchState = rememberSearchState()
) {
    Column() {
        CustomSearchBar(
            query = state.query,
            onQueryChange = { state.query = it },
            searchFocused = state.focused,
            onSearchFocusChange = { state.focused = it },
            onClearQuery = { state.query = TextFieldValue("") },
            searching = state.searching
        )
    }
}

enum class SearchDisplay {
    Categories, Suggestions, Results, NoResults
}

@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
//    categories: List<SearchCategoryCollection> = SearchRepo.getCategories(),
//    suggestions: List<SearchSuggestionGroup> = SearchRepo.getSuggestions(),
//    filters: List<Filter> = SnackRepo.getFilters(),
//    searchResults: List<Snack> = emptyList()
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
//            categories = categories,
//            suggestions = suggestions,
//            filters = filters,
//            searchResults = searchResults
        )
    }
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
//    categories: List<SearchCategoryCollection>,
//    suggestions: List<SearchSuggestionGroup>,
//    filters: List<Filter>,
//    searchResults: List<Snack>
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
//    var categories by mutableStateOf(categories)
//    var suggestions by mutableStateOf(suggestions)
//    var filters by mutableStateOf(filters)
//    var searchResults by mutableStateOf(searchResults)
    val searchDisplay: SearchDisplay
        get() = when {
            !focused && query.text.isEmpty() -> SearchDisplay.Categories
            focused && query.text.isEmpty() -> SearchDisplay.Suggestions
//            searchResults.isEmpty() -> SearchDisplay.NoResults
            else -> SearchDisplay.Results
        }
}