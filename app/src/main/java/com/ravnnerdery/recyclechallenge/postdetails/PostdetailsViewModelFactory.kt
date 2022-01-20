package com.ravnnerdery.recyclechallenge.postdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ravnnerdery.recyclechallenge.database.DatabaseDao
import com.ravnnerdery.recyclechallenge.postlist.PostlistViewModel
import java.lang.IllegalArgumentException

class PostdetailsViewModelFactory(
    private val dataSource: DatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PostdetailsViewModel::class.java)){
            return PostdetailsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}