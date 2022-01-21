package com.ravnnerdery.recyclechallenge.postlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.tables.Post


class PostButtonViewHolder(val postButton: Button): RecyclerView.ViewHolder(postButton)

class PostsAdapter(): RecyclerView.Adapter<PostButtonViewHolder>() {
    var data = listOf<Post>()

        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PostButtonViewHolder, position: Int) {
        val item = data[position]
        (item.title + " \n \n " + item.body).also { holder.postButton.text = it }
        holder.postButton.setOnClickListener{
            it.findNavController().navigate(PostlistFragmentDirections.actionPostlistFragmentToPostdetailsFragment(item.id))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostButtonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.button_view, parent, false) as Button
        return PostButtonViewHolder(view)
    }
}