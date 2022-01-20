package com.ravnnerdery.recyclechallenge.postdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.PostsDatabase
import com.ravnnerdery.recyclechallenge.databinding.PostdetailsFragmentBinding


class PostdetailsFragment : Fragment() {
    private lateinit var viewModel: PostdetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PostdetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.postdetails_fragment, container, false
        )
        val application = requireNotNull(this.activity).application

        val dataSource = PostsDatabase.getInstance(application).databaseDao
        val viewModelFactory = PostdetailsViewModelFactory(dataSource, application)
        val postdetailsViewModel = ViewModelProvider(this, viewModelFactory)
            .get(PostdetailsViewModel::class.java)
        binding.postdetailsViewModel = postdetailsViewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

}