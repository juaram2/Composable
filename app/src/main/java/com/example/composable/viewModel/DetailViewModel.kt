package com.example.composable.viewModel

import CloudHospitalApi.apis.HospitalsApi
import CloudHospitalApi.models.HospitalViewModel
import CloudHospitalApi.models.HospitalsViewModel
import CloudHospitalApi.models.MarketingType
import CloudHospitalApi.models.MediaType
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composable.service.ApiClients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    private val hospitalsApi = ApiClients.apiClient.createService(HospitalsApi::class.java)

    private val _hospital = MutableLiveData<HospitalViewModel>()
    val hospital: LiveData<HospitalViewModel>  = _hospital

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun fetchHospitalData(slug: String) {
        val actionName = "apiV1HospitalsSlugsSlugGet"
        _loading.value = true
        Log.d("debug", "${actionName} started.")
        viewModelScope.launch(Dispatchers.Main) {
            try {
                var result = hospitalsApi.apiV1HospitalsSlugsSlugGet(slug = slug)
                if (result.isSuccessful) {
                    _loading.value = false
                    Log.d("debug", "${actionName} completed.")
                    if (result.code() == 200) {
                        result.body()?.let { data ->
                            _hospital.postValue(data)
//                            _map.postValue(data.location!!)
//                            _rating.postValue(data.evaluations!!)
                            data.medias?.let { medias ->
                                val hospitalMedia =
                                    medias.filter { m -> m.order == 0 && m.mediaType == MediaType.photo }
                                        .first()

                                if (hospitalMedia != null) {
//                                    _hospitalImage.postValue(hospitalMedia.thumbnailUrl!!)
                                }
                            }

                        }
                    }
                } else {
                    _loading.value = false
                    Log.e("error", "code = ${result.code()} message: ${result.errorBody()?.string()}")
                }
            }
            catch (e: Exception) {
                _loading.value = false
                Log.d("debug", "${actionName} failed: ${e.message}")
            }
        }
    }
}