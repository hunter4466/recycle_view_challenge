package com.ravnnerdery.recyclechallenge.postlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ravnnerdery.recyclechallenge.database.DatabaseDao

class PostlistViewModel(
    var database: DatabaseDao,
    application: Application
): AndroidViewModel(application){

}