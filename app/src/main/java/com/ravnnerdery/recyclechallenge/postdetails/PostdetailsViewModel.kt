package com.ravnnerdery.recyclechallenge.postdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ravnnerdery.recyclechallenge.database.DatabaseDao

class PostdetailsViewModel(
    val database: DatabaseDao,
    application: Application
) : AndroidViewModel(application) {

}