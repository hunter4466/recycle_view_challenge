package com.ravnnerdery.recyclechallenge.postlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ravnnerdery.recyclechallenge.R

class postlist : Fragment() {

    companion object {
        fun newInstance() = postlist()
    }

    private lateinit var viewModel: PostlistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.postlist_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostlistViewModel::class.java)
        // TODO: Use the ViewModel
    }

}