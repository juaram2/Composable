package com.example.composable.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
abstract class BaseViewModel @Inject constructor() : ViewModel() {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

}