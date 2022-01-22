package com.ravnnerdery.recyclechallenge.postlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.database.tables.Post
import com.ravnnerdery.recyclechallenge.databinding.ButtonViewBinding


class PostsAdapter(private val clickListener: PostListener): ListAdapter<Post, PostsAdapter.ViewHolder>(PostListDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor (private val binding: ButtonViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Post, clickListener: PostListener){
            binding.post = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ButtonViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
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

class PostListener(val clickListener: (postId: Long) -> Unit){
    fun onClick(post: Post) = clickListener(post.id)
}