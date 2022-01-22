package com.ravnnerdery.recyclechallenge.postdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ravnnerdery.recyclechallenge.database.DatabaseDao

class PostdetailsViewModel(
    val database: DatabaseDao,
    application: Application,
    val id: Long
) : AndroidViewModel(application) {
    val commentsFromDatabase = database.getComments(id)
}