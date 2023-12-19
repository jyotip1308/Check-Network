package com.android.network_connectivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class mViewModel() : ViewModel(){

    var isDialogShown by mutableStateOf(false)
        private set

    fun showNetwork() {
        isDialogShown = true
    }

    fun hideNetwork() {
        isDialogShown = false
    }

}