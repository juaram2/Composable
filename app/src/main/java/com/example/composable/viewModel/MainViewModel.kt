package com.example.composable.viewModel

import CloudHospitalApi.apis.HospitalsApi
import CloudHospitalApi.models.HospitalsViewModel
import CloudHospitalApi.models.MarketingType
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composable.service.ApiClients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val hospitalsApi = ApiClients.apiClient.createService(HospitalsApi::class.java)

    private val _data = MutableLiveData<HospitalsViewModel>()
    val data: LiveData<HospitalsViewModel>
        get() = _data

    fun fetchData(marketingType: MarketingType?, page: Int = 1) {
        val actionName = "apiV1HospitalsGet"
        viewModelScope.launch(Dispatchers.Main) {
            try {
                var result = hospitalsApi.apiV1HospitalsGet(marketingType =  marketingType, page = page)
                if (result.isSuccessful) {
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
                        Log.d("debug", "$actionName failed: ${result.code()}")
                    }
                } else {
                    Log.d("debug", "$actionName failed")
                }
            }
            catch (e: Exception) {
                Log.d("debug", "$actionName failed: ${e.message}")
            }
        }
    }

}