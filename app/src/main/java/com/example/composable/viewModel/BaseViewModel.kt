package com.example.composable.viewModel

import CloudHospitalApi.infrastructure.Serializer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composable.model.exception.GrantValidationResult
import com.microsoft.appcenter.analytics.Analytics
import com.squareup.moshi.JsonAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
abstract class BaseViewModel @Inject constructor() : ViewModel() {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun decodeGrantValidationResult(actionName: String, data: ResponseBody?): GrantValidationResult? {
        val jsonAdapter: JsonAdapter<GrantValidationResult> = Serializer.moshi.adapter(
            GrantValidationResult::class.java).lenient()
        val grantValidationResult: GrantValidationResult? = jsonAdapter.lenient().fromJson(data?.source())

        Timber.e("$actionName errors: $grantValidationResult")
        val properties: MutableMap<String, String> = HashMap()
        properties["ErrorType"] = "GrantValidation"
        properties["ErrorBody"] = grantValidationResult.toString()
        Analytics.trackEvent(actionName, properties)
        return grantValidationResult
    }
}