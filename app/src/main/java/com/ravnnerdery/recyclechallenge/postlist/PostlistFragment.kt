package com.ravnnerdery.recyclechallenge.postlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.PostsDatabase
import com.ravnnerdery.recyclechallenge.database.tables.Post
import com.ravnnerdery.recyclechallenge.databinding.PostlistFragmentBinding

class PostlistFragment : Fragment() {

    private lateinit var posts: LiveData<List<Post>>
    private lateinit var buttonsContainer: RecyclerView

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
        binding.lifecycleOwner = this

        val adapter = PostsAdapter()
        binding.postListRecycler.adapter = adapter
        posts = postListViewModel.allPostsFromDatabase
        buttonsContainer = binding.postListRecycler

        posts.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })

        return binding.root
    }

}

