package com.ravnnerdery.recyclechallenge.postlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.PostsDatabase
import com.ravnnerdery.recyclechallenge.database.tables.Post
import com.ravnnerdery.recyclechallenge.databinding.PostlistFragmentBinding
import kotlinx.android.synthetic.main.postlist_fragment.view.*

class PostlistFragment : Fragment() {

    private lateinit var posts: LiveData<List<Post>>
    private lateinit var buttonsContainer: LinearLayout

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
        posts = postListViewModel.allPostsFromDatabase
        buttonsContainer = binding.buttonsScrollable
        posts.observe(this,{
            newData -> for(elm in newData){
                val newBtn = Button(context, null, R.style.btnStyle, R.style.btnStyle)
                newBtn.text = "${elm.title} \n \n ${elm.body}"
                newBtn.setOnClickListener{
                    requireView().findNavController().navigate(PostlistFragmentDirections.actionPostlistFragmentToPostdetailsFragment(elm.id))
                }
                buttonsContainer.addView(newBtn)
            }
        })

        return binding.root
    }

}

