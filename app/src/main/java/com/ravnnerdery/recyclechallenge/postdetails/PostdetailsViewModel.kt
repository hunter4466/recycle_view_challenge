package com.ravnnerdery.recyclechallenge.postdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ravnnerdery.recyclechallenge.database.DatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class PostdetailsViewModel(
    val database: DatabaseDao,
    application: Application,
    val id: Long
) : AndroidViewModel(application) {
    val commentsFromDatabase = database.getComments(id)
}