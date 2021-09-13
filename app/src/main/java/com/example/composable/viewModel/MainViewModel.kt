package com.example.composable.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _myModels = MutableLiveData<Boolean>()
    val myModels: LiveData<Boolean> = _myModels

    private val _onItemClickEvent = MutableLiveData<Boolean>()
    val onItemClickEvent: LiveData<Boolean> = _onItemClickEvent

}