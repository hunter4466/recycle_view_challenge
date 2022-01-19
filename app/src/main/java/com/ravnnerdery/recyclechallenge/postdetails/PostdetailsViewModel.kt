package com.ravnnerdery.recyclechallenge.postdetails

import android.util.Log
import androidx.lifecycle.ViewModel

class PostdetailsViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.i("PostdetailsViewModel", "PostdetailsViewModel Cleared")
    }

}