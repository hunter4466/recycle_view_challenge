package com.ravnnerdery.recyclechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.ravnnerdery.recyclechallenge.database.DatabaseDao
import com.ravnnerdery.recyclechallenge.postlist.PostlistViewModel

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}