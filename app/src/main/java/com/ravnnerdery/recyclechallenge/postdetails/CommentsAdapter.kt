package com.ravnnerdery.recyclechallenge.postdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.tables.Comment

class CommentsAdapter: ListAdapter<Comment, CommentsAdapter.ViewHolder>(CommentsDiffCallBack()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

        class ViewHolder private constructor (val commentTextView: TextView): RecyclerView.ViewHolder(commentTextView){
        fun bind(item: Comment){
            (item.name + " \n \n "+ item.email+ " \n \n " + item.body+ " \n \n " + "----------------------------------").also { commentTextView.text = it }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.comment_text, parent, false) as TextView
                return ViewHolder(view)
            }
        }
    }


}

class CommentsDiffCallBack : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }
}