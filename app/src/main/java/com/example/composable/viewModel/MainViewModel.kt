package com.example.composable.viewModel

import CloudHospitalApi.apis.HospitalsApi
import CloudHospitalApi.models.HospitalViewModel
import CloudHospitalApi.models.HospitalsViewModel
import CloudHospitalApi.models.MarketingType
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composable.service.ApiClients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    private val hospitalsApi = ApiClients.apiClient.createService(HospitalsApi::class.java)

    private val _data = MutableLiveData<HospitalsViewModel>()
    val data: LiveData<HospitalsViewModel> = _data

    private val _hospital = MutableLiveData<HospitalViewModel>()
    val hospital: LiveData<HospitalViewModel> = _hospital

    init {
        fetchData(MarketingType.both)
    }

    private fun fetchData(marketingType: MarketingType?, page: Int = 1) {
        val actionName = "apiV1HospitalsGet"
        Log.d("debug", "$actionName started.")
        _loading.postValue(true)
        viewModelScope.launch(Dispatchers.Main) {
            try {
                var result = hospitalsApi.apiV1HospitalsGet(marketingType =  marketingType, page = page)
                if (result.isSuccessful) {
                    _loading.postValue(false)
                    if (result.code() == 200) {
                        result.body()?.let { data ->
                            if (_data.value?.items != null && page > 1) {
                                val oldItems = _data.value?.items?.toMutableList()

                                if (data.items != null)
                                    oldItems?.addAll(data.items!!)

                                val newData = HospitalsViewModel(
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

    fun fetchHospitalItem(slug: String) {
        val actionName = "apiV1HospitalsSlugsSlugGet"
        _loading.value = true
        Log.d("debug", "$actionName started.")
        viewModelScope.launch(Dispatchers.Main) {
            try {
                var result = hospitalsApi.apiV1HospitalsSlugsSlugGet(slug = slug)
                if (result.isSuccessful) {
                    _loading.value = false
                    Log.d("debug", "$actionName completed.")
                    if (result.code() == 200) {
                        result.body()?.let { data ->
                            _hospital.postValue(data)
                        }
                    }
                } else {
                    _loading.value = false
                    Log.e("error",
                        "code = ${result.code()} message: ${result.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _loading.value = false
                Log.d("debug", "$actionName failed: ${e.message}")
            }
        }
    }
}