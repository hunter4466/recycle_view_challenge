package com.ravnnerdery.recyclechallenge.postdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ravnnerdery.recyclechallenge.database.DatabaseDao
import java.lang.IllegalArgumentException

class PostdetailsViewModelFactory(
    private val dataSource: DatabaseDao,
    private val application: Application,
    private val id: Long
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PostdetailsViewModel::class.java)){
            return PostdetailsViewModel(dataSource, application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}