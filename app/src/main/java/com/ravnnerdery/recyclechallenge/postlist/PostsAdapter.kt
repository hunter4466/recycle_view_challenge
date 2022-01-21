package com.ravnnerdery.recyclechallenge.postlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.tables.Post
import kotlinx.android.synthetic.main.comment_text.view.contentText
import kotlinx.android.synthetic.main.comment_text.view.titleText


class PostsAdapter: ListAdapter<Post, PostsAdapter.ViewHolder>(PostListDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor (val postButton: LinearLayout): RecyclerView.ViewHolder(postButton){
        fun bind(item: Post){
            postButton.titleText.text = item.title[0].uppercaseChar() + item.title.substring(1)
            postButton.contentText.text = item.body[0].uppercaseChar() + item.body.substring(1)
            postButton.setOnClickListener{
                it.findNavController().navigate(PostlistFragmentDirections.actionPostlistFragmentToPostdetailsFragment(item.id))
            }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.button_view, parent, false) as LinearLayout
                return ViewHolder(view)
            }
        }
    }

}

class PostListDiffCallBack : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
}
