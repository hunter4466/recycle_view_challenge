package com.ravnnerdery.recyclechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.ravnnerdery.recyclechallenge.database.DatabaseDao
import com.ravnnerdery.recyclechallenge.postlist.PostlistViewModel

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    companion object {
        lateinit var database: DatabaseDao
    }

    override fun onDestroy() {
        database.deletePosts()
        database.deleteComments()
        println("DESTROYED DATABASE")
        super.onDestroy()
    }
}