package com.ravnnerdery.recyclechallenge.postdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.PostsDatabase
import com.ravnnerdery.recyclechallenge.database.tables.Comment
import com.ravnnerdery.recyclechallenge.databinding.PostdetailsFragmentBinding
import com.ravnnerdery.recyclechallenge.postlist.PostlistFragmentDirections


class PostdetailsFragment : Fragment() {
    private lateinit var viewModel: PostdetailsViewModel
    private lateinit var commentsContainer: LinearLayout
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
        binding.postdetailsViewModel = postdetailsViewModel
        binding.setLifecycleOwner(this)

        comments = postdetailsViewModel.commentsFromDatabase
        commentsContainer = binding.commentsContainer
        comments.observe(this,{
                newData -> for(elm in newData){
            val newTextField = TextView(context, null, R.style.btnStyle, R.style.btnStyle)
            newTextField.text = "${elm.name} \n \n ${elm.email} \n \n ${elm.body} \n \n"
            newTextField.setOnClickListener{
                requireView().findNavController().navigate(PostlistFragmentDirections.actionPostlistFragmentToPostdetailsFragment(elm.id))
            }
            commentsContainer.addView(newTextField)
        }
        })



        return binding.root
    }

}