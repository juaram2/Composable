package com.example.composable.viewModel

import CloudHospitalApi.apis.ArticlesApi
import CloudHospitalApi.models.ArticlesViewModel
import CloudHospitalApi.models.MarketingType
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composable.service.ApiClients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel : BaseViewModel() {
    private val articlesApi = ApiClients.apiClient.createService(ArticlesApi::class.java)

    private val _data = MutableLiveData<ArticlesViewModel>()
    val data: LiveData<ArticlesViewModel> = _data

    init {
        fetchData(MarketingType.both)
    }

    private fun fetchData(marketingType: MarketingType?, page: Int = 1) {
        val actionName = "apiV1HospitalsGet"
        Log.d("debug", "$actionName started.")
        _loading.postValue(true)
        viewModelScope.launch(Dispatchers.Main) {
            try {
                var result = articlesApi.apiV1HospitalsArticlesGet(marketingType =  marketingType, page = page)
                if (result.isSuccessful) {
                    _loading.postValue(false)
                    if (result.code() == 200) {
                        result.body()?.let { data ->
                            if (_data.value?.items != null && page > 1) {
                                val oldItems = _data.value?.items?.toMutableList()

                                if (data.items != null)
                                    oldItems?.addAll(data.items!!)

                                val newData = ArticlesViewModel(
                                    items = oldItems,
                                    metaData = data.metaData
                                )

                                _data.postValue(newData)
                            } else {
                                _data.postValue(data)
                            }
                        }
                    } else {
                        _loading.postValue(false)
                        Log.d("debug", "$actionName failed: ${result.code()}")
                    }
                } else {
                    _loading.postValue(false)
                    Log.d("debug", "$actionName failed")
                }
            }
            catch (e: Exception) {
                _loading.postValue(false)
                Log.d("debug", "$actionName failed: ${e.message}")
            }
        }
    }
}