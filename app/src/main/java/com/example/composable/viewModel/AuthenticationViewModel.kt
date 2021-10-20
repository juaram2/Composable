package com.example.composable.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composable.model.exception.GrantValidationResult
import com.example.composable.model.identity.IdentityToken
import com.example.composable.service.AccountsApi
import com.example.composable.service.ApiClients
import com.example.composable.service.AuthsApi
import com.example.composable.utils.Constants
import com.example.composable.utils.PrefUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthenticationViewModel: ViewModel() {

    private val authsApi = ApiClients.identityApiClient.createService(AuthsApi::class.java)
    private val accountsApi = ApiClients.identityApiClient.createService(AccountsApi::class.java)

    val email = MutableLiveData<String?>()
    val password = MutableLiveData<String?>()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _signedIn = MutableLiveData<Boolean>(false)
    val signedIn: LiveData<Boolean> = _signedIn

    private val _signInError = MutableLiveData<GrantValidationResult>()
    val signInError: LiveData<GrantValidationResult> = _signInError

    fun onClickSignin() {
        val actionName = "signInWithEmail"
        Log.d("debug", "$actionName started")
        _loading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = authsApi.signinWithEmail(Constants.clientId(),
                Constants.clientSecret,
                Constants.scope,
                "password",
                email.value.toString(),
                password.value.toString())
            try {
                _loading.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        PrefUtil.cacheIdentityToken(IdentityToken(it.access_token,
                            it.expires_in,
                            it.token_type,
                            it.refresh_token,
                            it.scope))
                        Log.d("debug", "$actionName: ${response.body()}")
                        _signedIn.postValue(true)
                    }
                } else {
                    Log.d("debug", "$actionName failed}")
//                    val grantValidationResult: GrantValidationResult? = decodeGrantValidationResult(
//                        actionName,
//                        response.errorBody())
//                    _signInError.postValue(grantValidationResult)
                }
            } catch (e: java.lang.Exception) {
                // TODO: Handle connection or communication error, report to AppCenter
                _loading.value = false
                Log.e("error", "$actionName: ${e.localizedMessage}")
            }
        }
    }


}

