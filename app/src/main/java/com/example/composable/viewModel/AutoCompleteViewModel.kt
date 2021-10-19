package com.example.composable.viewModel

import CloudHospitalApi.apis.SearchApi
import CloudHospitalApi.models.AzureSearchServiceAutocompleteModel
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composable.service.ApiClients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AutoCompleteViewModel : BaseViewModel() {
    private val searchApi = ApiClients.apiClient.createService(SearchApi::class.java)

    private val handler = Handler()

    private val _autoComplete = MutableLiveData<AzureSearchServiceAutocompleteModel>()
    val autoComplete: LiveData<AzureSearchServiceAutocompleteModel> = _autoComplete

    private val _searchTerm = MutableLiveData<String>()
    var searchTerm: LiveData<String> = _searchTerm


    fun fetchData(searchTerm: String) {
        _loading.value = true
        val actionName = "apiV1SearchAutocompleteGet"
        Log.d("debug", "$actionName started.")
        Log.d("debug", "searchTerm: $searchTerm")

        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            viewModelScope.launch(Dispatchers.Main) {
                searchTerm?.let { keyword ->
                    var result = searchApi.apiV1SearchAutocompleteGet(keyword = keyword)
                    try {
                        if (result.isSuccessful) {
                            _loading.value = false
                            Log.d("debug", "$actionName completed.")
                            if (result.code() == 200) {
                                result.body()?.let { data ->
                                    _autoComplete.postValue(data)
                                }
                            }
                        } else {
                            _loading.value = false
                            Log.e("error", "code = ${result.code()} message: ${result.errorBody()?.string()}")
                        }
                    }
                    catch (e: Exception) {
                        _loading.value = false
                        Log.d("debug", "$actionName failed: ${e.message}")
                    }
                }
            }
        }, 500)
    }
}