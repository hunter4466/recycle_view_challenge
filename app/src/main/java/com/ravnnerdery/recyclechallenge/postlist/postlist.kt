package com.ravnnerdery.recyclechallenge.postlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.PostsDatabase
import com.ravnnerdery.recyclechallenge.databinding.PostlistFragmentBinding

class postlist : Fragment() {

    companion object {
        fun newInstance() = postlist()
    }

    private lateinit var viewModel: PostlistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(PostlistViewModel::class.java)
        val binding: PostlistFragmentBinding = DataBindingUtil.inflate(
            inflater,R.layout.postlist_fragment, container, false
        )
        //val binding = inflater.inflate(R.layout.postlist_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = PostsDatabase.getInstance(application).databaseDao
        val viewModelFactory = PostlistViewModelFactory(dataSource, application)
        val postListViewModel = ViewModelProviders.of(this, viewModelFactory)[PostlistViewModel::class.java]

        binding.setLifecycleOwner(this)

        return binding.root
    }

}