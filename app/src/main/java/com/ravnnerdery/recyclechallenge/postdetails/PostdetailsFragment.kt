package com.ravnnerdery.recyclechallenge.postdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ravnnerdery.recyclechallenge.R

class PostdetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PostdetailsFragment()
    }

    private lateinit var viewModel: PostdetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostdetailsViewModel::class.java)
        return inflater.inflate(R.layout.postdetails_fragment, container, false)
    }

}