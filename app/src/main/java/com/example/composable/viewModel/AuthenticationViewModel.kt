package com.example.composable.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.composable.BuildConfig
//import com.cloudhospital.R
//import com.cloudhospital.model.account.RegisterViewModel
import com.example.composable.model.exception.GrantValidationResult
import com.example.composable.model.exception.IdentityError
import com.example.composable.model.identity.IdentityToken
//import com.cloudhospital.model.exception.RestException
//import com.cloudhospital.model.externalLogin.ExternalLoginInfo
//import com.cloudhospital.model.externalLogin.ExternalLogins
//import com.cloudhospital.model.identity.ExternalSignIn
//import com.example.composable.model.identity.IdentityToken
//import com.example.composable.model.identity.Provider
import com.example.composable.service.AccountsApi
import com.example.composable.service.ApiClients
import com.example.composable.service.AuthsApi
//import com.cloudhospital.service.ExternalLoginsApi
//import com.cloudhospital.utils.Constants
//import com.cloudhospital.utils.NoConnectionInterceptor
//import com.cloudhospital.utils.PrefUtil
//import com.cloudhospital.utils.Utils
import com.example.composable.model.identity.EmailSign
import com.example.composable.model.identity.UserInfo
import com.example.composable.utils.Constants
import com.example.composable.utils.PrefUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class AuthenticationViewModel() : BaseViewModel() {
//    private val _application = application

    private val authsApi = ApiClients.identityApiClient.createService(AuthsApi::class.java)
    private val accountsApi = ApiClients.identityApiClient.createService(AccountsApi::class.java)
//    private val externalLoginsApi = ApiClients.identityApiClient.createService(ExternalLoginsApi::class.java)

    var email = MutableLiveData<String?>()
    val password = MutableLiveData<String?>()
    val confirmPassword = MutableLiveData<String?>()
    val code = MutableLiveData<String?>()

    private val _startGoogleSigninActivity = MutableLiveData<Boolean>()
    val startGoogleSigninActivity: LiveData<Boolean>
        get() = _startGoogleSigninActivity

    private val _startFacebookSigninActivity = MutableLiveData<Boolean>()
    val startFacebookSigninActivity: LiveData<Boolean>
        get() = _startFacebookSigninActivity

    private val _signUpError = MutableLiveData<List<IdentityError>>()
    val signUpError: LiveData<List<IdentityError>>
        get() = _signUpError

    val _signInError = MutableLiveData<GrantValidationResult>()
    val signInError: LiveData<GrantValidationResult>
        get() = _signInError

    val _signedIn = MutableLiveData<Boolean>(false)
    val signedIn: LiveData<Boolean>
        get() = _signedIn

    private val _isValid = MutableLiveData<Boolean>(false)
    val isValid: LiveData<Boolean> = _isValid

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _errorPwd = MutableLiveData<String?>()
    val errorPwd: LiveData<String?> = _errorPwd

    private val _errorPwdConfirm = MutableLiveData<String?>()
    val errorPwdConfirm: LiveData<String?> = _errorPwdConfirm

    private val _navigateToSignup =  MutableLiveData<Boolean>()
    /**
     * If this is true, immediately navigate to [SignUp] and call [doneNavigating]
     */
    val navigateToSignUp: LiveData<Boolean>
        get() = _navigateToSignup

    private val _navigateToSignin =  MutableLiveData<Boolean>()

    private val _navigateToFindPassword = MutableLiveData<Boolean>()
    val navigateToFindPassword: LiveData<Boolean> = _navigateToFindPassword

    /**
     * If this is true, immediately navigate to [SignIn] and call [doneNavigating]
     */
    val navigateToSignin: LiveData<Boolean>
        get() = _navigateToSignin

    private val _successSendResetEmail = MutableLiveData<Boolean>()
    val successSendResetEmail: LiveData<Boolean> = _successSendResetEmail

//    private val _getExternalLoginInfosError = MutableLiveData<RestException>()
//    val getExternalLoginInfosError: LiveData<RestException> = _getExternalLoginInfosError
//
//    private val _externalLoginInfos = MutableLiveData<ExternalLogins?>()
//    val externalLoginInfos: LiveData<ExternalLogins?> = _externalLoginInfos

    fun resetTextInputs() {
        email.postValue(null)
        password.postValue(null)
        confirmPassword.postValue(null)
    }

    fun doneNavigation() {
        resetTextInputs()
        _navigateToSignup.value = false
        _navigateToSignin.value = false
    }

    fun emailValidation() {
//        if(!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
//            _error.postValue("The email format does not match.")
//        } else {
//            _error.postValue("")
//        }
//        validation()
    }

    fun passwordValidation() {
//        if(!Utils.passwordPattern.matcher(password.value).matches()) {
//            _errorPwd.postValue("The password format does not match.")
//        } else {
//            _errorPwd.postValue("")
//        }
//        validation()
    }

    fun passwordConfirmValidation() {
//        if(!Utils.passwordPattern.matcher(confirmPassword.value).matches()) {
//            _errorPwdConfirm.postValue("The password format does not match.")
//        } else {
//            _errorPwdConfirm.postValue("")
//        }
//        validation()
    }

    private fun validation() {
//        if (email.value.isNullOrBlank() ||
//            password.value.isNullOrBlank() ||
//            confirmPassword.value.isNullOrBlank() ||
//            !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() ||
//            !Utils.passwordPattern.matcher(password.value).matches() ||
//            !Utils.passwordPattern.matcher(confirmPassword.value).matches())
//        {
//            _isValid.postValue(false)
//        }
//        else if(password.value != confirmPassword.value) {
//            _errorPwdConfirm.postValue("The confirm password does not match.")
//            _isValid.postValue(false)
//        }
//        else {
//            _isValid.postValue(true)
//            _errorPwdConfirm.postValue("")
//        }
    }

    fun onClickSignup() {
//        val actionName = "apiV1AccountsPost"
//        Timber.d(actionName)
//        _loading.value = true
//        viewModelScope.launch(Dispatchers.Main) {
//            val registerViewModel = RegisterViewModel(email.value.toString(),
//                password.value.toString(),
//                confirmPassword = confirmPassword.value)
//            val response = accountsApi.apiV1AccountsPost(registerViewModel)
//            try {
//                _loading.value = false
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        Timber.d("$actionName: ${response.body()}")
//                        onClickSignin()
//                    }
//                } else {
//                    Timber.e("$actionName: ${response.errorBody()}")
//                    val identityErrors = decodeIdentityError(actionName, response.errorBody())
//                    _signUpError.postValue(identityErrors)
//                    response.message()
//                }
//            } catch (e: java.lang.Exception) {
//                _loading.value = false
//                Timber.e("$actionName: ${e.localizedMessage}")
//                Timber.d("apiV1AccountsPost errors!!!!____${response.errorBody()?.string()}")
//                val identityErrors: List<IdentityError>? = decodeIdentityError(actionName,
//                    response.errorBody())
//                _signUpError.postValue(identityErrors)
//            }
//        }
    }

    fun onClickSignin() {
        val actionName = "signInWithEmail"
        Timber.d("$actionName started")
        _loading.value = true
//        Timber.d("$email started")
//        Timber.d("$password started")
//        Timber.d("UserInfo : $userInfo")
        //Log.d("Debug", "started : "+userInfo.toString())

        viewModelScope.launch(Dispatchers.Main) {
            val response = authsApi.signinWithEmail(
                Constants.clientId(),
                Constants.clientSecret,
                Constants.scope,
                "password",
                email.value.toString(),
                password.value.toString()
            )
            Timber.d("$email started")
            Timber.d("$password started")
            Log.e("Debug","response : "+response.toString())
            try {
                _loading.value = false
                Log.e("Debug","responseTry : "+response.toString())
                if (response.isSuccessful) {
                    response.body()?.let {
                        PrefUtil.cacheIdentityToken(
                            IdentityToken(
                                it.access_token,
                                it.expires_in,
                                it.token_type,
                                it.refresh_token,
                                it.scope
                            )
                        )
                        Log.e("Debug", "IdentityToken : "+response.body().toString())
                        Timber.d("$actionName: ${response.body()}")
                        _signedIn.postValue(true)

                    }
                } else {
                    val grantValidationResult: GrantValidationResult? = decodeGrantValidationResult(
                        actionName,
                        response.errorBody()
                    )
                    _signInError.postValue(grantValidationResult)
                }
            } catch (e: Exception) {
                Log.e("Exception", e.message!! )
                Log.e("Exception", e.localizedMessage )
                // TODO: Handle connection orcommunication error, report to AppCenter
                _loading.value = false
                Timber.e("$actionName: ${e.localizedMessage}")
            }
        }
    }
//
//    fun fetchExternalLogins() {
//        _loading.value = true
//        val actionName = "apiV1ExternalLoginsGet"
//        Timber.d("$actionName started")
//        viewModelScope.launch(Dispatchers.Main) {
//            var response = externalLoginsApi.apiV1ExternalLoginsGet()
//            try {
//                if (response.isSuccessful) {
//                    _loading.value = false
//                    response.body()?.let { data ->
//                        Timber.d("$actionName: ${response.body()}")
//                        _externalLoginInfos.postValue(data)
//                    }
//                } else {
//                    _loading.value = false
//                    val externalLoginInfosError = decodeRestException(actionName, response.errorBody())
//                    _getExternalLoginInfosError.postValue(externalLoginInfosError)
//                }
//            }
//            catch (e: NoConnectionInterceptor.NoConnectivityException){
//                Timber.e(e.message)
//                setConnectionState(false)
//            }
//            catch (e: NoConnectionInterceptor.NoInternetException){
//                Timber.e(e.message)
//                setInternetAvailable(false)
//            }
//            catch (e: Exception) {
//                Timber.e("$actionName: ${e.localizedMessage}")
//                _loading.value = false
//            }
//        }
//    }
//
//    fun onClickSigninFacebook() {
//        _startFacebookSigninActivity.postValue(true)
//    }
//
//    fun onClickSigninGoogle() {
//        _startGoogleSigninActivity.postValue(true)
//    }
//
//    fun externalSigninActivityStarted() {
//        _startFacebookSigninActivity.postValue(false)
//        _startGoogleSigninActivity.postValue(false)
//    }
//
//    fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
//        _loading.value = true
//        try {
//            val account = completedTask.getResult(ApiException::class.java)
//            Timber.d("account: ${account?.account}")
//            reportAppCenter("handleGoogleSignInResult account", account?.account.toString())
//
//            if (account != null) {
//                reportAppCenter("handleGoogleSignInResult account not null", account.account.toString())
//                _loading.value = false
//                var access_token: String
//                viewModelScope.launch(Dispatchers.IO) {
//                    access_token = GoogleAuthUtil.getToken(_application.applicationContext, account.account, "oauth2:profile email")
//                    reportAppCenter("handleGoogleSignInResult access token", access_token)
//
//                    Timber.d("access_token: $access_token")
//
//                    viewModelScope.launch(Dispatchers.Main) {
//                        val externalSignin = ExternalSignIn(provider = Provider.Google, sub = "", email = account.email.toString(), external_token = access_token)
//                        reportAppCenter("handleGoogleSignInResult externalSignin", externalSignin.toString())
//                        signinExternal(externalSignin)
//                    }
//                }
//            } else {
//                _loading.value = false
//                Toast.makeText(_application.applicationContext, R.string.retry, Toast.LENGTH_SHORT).show()
//            }
//        } catch (e: ApiException) {
//            _loading.value = false
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Timber.w("signInResult:failed code=" + e.statusCode)
//            Toast.makeText(_application.applicationContext, R.string.google_sign_in_cancelled, Toast.LENGTH_SHORT).show()
//
//            reportAppCenter("handleGoogleSignInResult ApiException", e.localizedMessage)
//        }
//        catch (e: NullPointerException) {
//            _loading.value = false
//            Timber.w("signInResult:failed code=" + e.localizedMessage)
//            Toast.makeText(_application.applicationContext, R.string.retry, Toast.LENGTH_SHORT).show()
//
//            reportAppCenter("handleGoogleSignInResult NullPointException", e.localizedMessage)
//        }
//    }
//
//    fun signinExternal(externalSignIn: ExternalSignIn) {
//        val actionName = "signInExternal"
//        Timber.d(actionName)
//        _loading.value = true
//        Timber.i("provider: ${externalSignIn.provider}")
//        Timber.i("email: ${externalSignIn.email}")
//        Timber.i("external_token: ${externalSignIn.external_token}")
//
//        viewModelScope.launch(Dispatchers.Main) {
//            val response = authsApi.signInExternal(
//                Constants.clientId(),
//                Constants.clientSecret,
//                Constants.scope,
//                "",
//                externalSignIn.grant_type.toString(),
//                provider = externalSignIn.provider.toString(),
//                external_token = externalSignIn.external_token,
//                email = externalSignIn.email)
//
//            try {
//                _loading.value = false
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        PrefUtil.cacheIdentityToken(IdentityToken(it.access_token,
//                            it.expires_in,
//                            it.token_type,
//                            it.refresh_token,
//                            it.scope))
//                        Timber.d("IdentityToken: ${response.body()}")
//                        _signedIn.postValue(true)
//                    }
//                } else {
//                    val grantValidationResult: GrantValidationResult? = decodeGrantValidationResult(
//                        actionName,
//                        response.errorBody())
//                    _signInError.postValue(grantValidationResult)
//                }
//            } catch (e: java.lang.Exception) {
//                // TODO: Handle connection orcommunication error, report to AppCenter
//                _loading.value = false
//                Timber.e("$actionName: ${e.localizedMessage}")
//            }
//        }
//    }
//
//    fun connectGoogleSignIn(completedTask: Task<GoogleSignInAccount>) {
//        try {
//            val account = completedTask.getResult(ApiException::class.java)
//
//            if (account != null) {
//                var access_token: String
//                viewModelScope.launch(Dispatchers.IO) {
//                    access_token = GoogleAuthUtil.getToken(_application.applicationContext, account.account, "oauth2:profile email")
//
//                    Timber.d("access_token: ${access_token}")
//                    viewModelScope.launch(Dispatchers.Main) {
//                        val externalLoginInfo = ExternalLoginInfo(loginProvider = Provider.Google.name, providerDisplayName = Provider.Google.name, providerKey = access_token)
//                        connectExternalLogin(externalLoginInfo)
//                    }
//                }
//            }
//        } catch (e: ApiException) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Timber.w("signInResult:failed code=" + e.statusCode)
//            Toast.makeText(_application.applicationContext, R.string.google_sign_in_cancelled, Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    fun connectExternalLogin(externalLoginInfo: ExternalLoginInfo) {
//        val actionName = "apiV1ExternalLoginsPost"
//        Timber.d(actionName)
//        _loading.value = true
//        Timber.i("loginProvider: ${externalLoginInfo.loginProvider}")
//        Timber.i("providerDisplayName: ${externalLoginInfo.providerDisplayName}")
//        Timber.i("providerKey: ${externalLoginInfo.providerKey}")
//
//        viewModelScope.launch(Dispatchers.Main) {
//            val response = externalLoginsApi.apiV1ExternalLoginsPost(externalLoginInfo = externalLoginInfo)
//
//            try {
//                _loading.value = false
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        Timber.d("ExternalLogin connected.")
//                        fetchExternalLogins()
//                    }
//                } else {
//                    val grantValidationResult: GrantValidationResult? = decodeGrantValidationResult(
//                            actionName,
//                            response.errorBody())
//                    _signInError.postValue(grantValidationResult)
//                }
//            } catch (e: java.lang.Exception) {
//                // TODO: Handle connection orcommunication error, report to AppCenter
//                _loading.value = false
//                Timber.e("$actionName: ${e.localizedMessage}")
//            }
//        }
//    }
//
//    fun removeExternalLogin(externalLoginInfo: ExternalLoginInfo) {
//        val actionName = "apiV1ExternalLoginsPost"
//        Timber.d(actionName)
//        _loading.value = true
//        Timber.i("loginProvider: ${externalLoginInfo.loginProvider}")
//        Timber.i("providerDisplayName: ${externalLoginInfo.providerDisplayName}")
//        Timber.i("providerKey: ${externalLoginInfo.providerKey}")
//
//        viewModelScope.launch(Dispatchers.Main) {
//            val response = externalLoginsApi.apiV1ExternalLoginsDelete(externalLoginInfo = externalLoginInfo)
//
//            try {
//                _loading.value = false
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        Timber.d("ExternalLogin removed.")
//                        fetchExternalLogins()
//                    }
//                } else {
//                    val grantValidationResult: GrantValidationResult? = decodeGrantValidationResult(
//                        actionName,
//                        response.errorBody())
//                }
//            } catch (e: java.lang.Exception) {
//                // TODO: Handle connection orcommunication error, report to AppCenter
//                _loading.value = false
//                Timber.e("$actionName: ${e.localizedMessage}")
//            }
//        }
//    }

    fun onClickForgotPassword() {
        _navigateToFindPassword.postValue(true)
    }

    fun onClickNavigateToSignUp() {
        _navigateToSignup.value = true
    }

    fun onClickNavigateToSignIn() {
        Timber.d("onClickNavigateToSignIn")
        _navigateToSignin.value = true
    }

    fun signInErrorsNotified() {
        _signInError.postValue(null)
    }

    fun signUpErrorsNotified() {
        _signUpError.postValue(null)
    }
}