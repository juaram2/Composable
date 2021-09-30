package com.example.composable.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel @ViewModelInject constructor() : ViewModel() {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

}