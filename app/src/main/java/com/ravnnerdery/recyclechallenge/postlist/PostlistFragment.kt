package com.ravnnerdery.recyclechallenge.postlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.PostsDatabase
import com.ravnnerdery.recyclechallenge.database.tables.Post
import com.ravnnerdery.recyclechallenge.databinding.PostlistFragmentBinding

class PostlistFragment : Fragment() {
    private lateinit var viewModel: PostlistViewModel
    private lateinit var posts: Post

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PostlistFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.postlist_fragment, container, false
        )
        val application = requireNotNull(this.activity).application

        val dataSource = PostsDatabase.getInstance(application).databaseDao
        val viewModelFactory = PostlistViewModelFactory(dataSource, application)
        val postListViewModel = ViewModelProvider(this, viewModelFactory)
            .get(PostlistViewModel::class.java)
        binding.postListViewModel = postListViewModel
        binding.setLifecycleOwner(this)


        return binding.root
    }

}