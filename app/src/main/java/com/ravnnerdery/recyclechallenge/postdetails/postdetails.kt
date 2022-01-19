package com.ravnnerdery.recyclechallenge.postdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ravnnerdery.recyclechallenge.R

class postdetails : Fragment() {

    companion object {
        fun newInstance() = postdetails()
    }

    private lateinit var viewModel: PostdetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.postdetails_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostdetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}