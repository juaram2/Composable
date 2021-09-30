package com.example.composable.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

}