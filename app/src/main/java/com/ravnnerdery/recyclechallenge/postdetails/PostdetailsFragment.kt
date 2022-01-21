package com.ravnnerdery.recyclechallenge.postdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.PostsDatabase
import com.ravnnerdery.recyclechallenge.database.tables.Comment
import com.ravnnerdery.recyclechallenge.databinding.PostdetailsFragmentBinding
import com.ravnnerdery.recyclechallenge.postlist.PostlistFragmentDirections


class PostdetailsFragment : Fragment() {
    private lateinit var commentsContainer: RecyclerView
    private lateinit var comments: LiveData<List<Comment>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PostdetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.postdetails_fragment, container, false
        )
        val application = requireNotNull(this.activity).application
        val args = PostdetailsFragmentArgs.fromBundle(requireArguments())
        val dataSource = PostsDatabase.getInstance(application).databaseDao
        val viewModelFactory = PostdetailsViewModelFactory(dataSource, application, args.id)
        val postdetailsViewModel = ViewModelProvider(this, viewModelFactory)
            .get(PostdetailsViewModel::class.java)
        val swypeContainer = binding.postDetailsSwypeContainer
        binding.postdetailsViewModel = postdetailsViewModel

        val adapter = CommentsAdapter()
        binding.commentsRecycler.adapter = adapter
        comments = postdetailsViewModel.commentsFromDatabase
        commentsContainer = binding.commentsRecycler

        comments.observe(viewLifecycleOwner,{
            it?.let{
                adapter.submitList(it)
            }
        })
        swypeContainer.setOnRefreshListener {
            adapter.notifyDataSetChanged()
            comments.observe(viewLifecycleOwner, {
                swypeContainer.isRefreshing = false
            })
        }



        return binding.root
    }

}