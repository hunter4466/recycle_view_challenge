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
import androidx.navigation.fragment.findNavController
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.PostsDatabase
import com.ravnnerdery.recyclechallenge.database.tables.Post
import com.ravnnerdery.recyclechallenge.databinding.PostlistFragmentBinding

class PostlistFragment : Fragment() {
    private lateinit var posts: LiveData<List<Post>>
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
        val postListViewModel = ViewModelProvider(this, viewModelFactory)[PostlistViewModel::class.java]


        val swypeContainer = binding.postListSwypeContainer
        binding.postListViewModel = postListViewModel
        binding.lifecycleOwner = this

        val adapter = PostsAdapter(PostListener {
            id -> postListViewModel.onPostClicked(id)
        })
        binding.postListRecycler.adapter = adapter

        postListViewModel.allPostsFromDatabase.also {
            if (it != null) {
                posts = it
            }
        }
        posts.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        postListViewModel.navigateToDetails.observe(this, Observer { post ->
            post?.let {
                this.findNavController().navigate(PostlistFragmentDirections.actionPostlistFragmentToPostdetailsFragment(post))
                postListViewModel.onPostDetailsNavigated()
            }
        })

        swypeContainer.setOnRefreshListener {
            adapter.notifyDataSetChanged()
            posts.observe(viewLifecycleOwner, {
                swypeContainer.isRefreshing = false
            })
        }

        return binding.root
    }

}

