package com.ravnnerdery.recyclechallenge.postdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.tables.Comment
import kotlinx.android.synthetic.main.comment_text.view.*

class CommentsAdapter : ListAdapter<Comment, CommentsAdapter.ViewHolder>(CommentsDiffCallBack()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val commentLinearLayout: LinearLayout) :
        RecyclerView.ViewHolder(commentLinearLayout) {
        fun bind(item: Comment) {
            commentLinearLayout.titleText.text =
                item.name[0].uppercaseChar() + item.name.substring(1)
            commentLinearLayout.emailText.text = item.email
            commentLinearLayout.contentText.text =
                item.body[0].uppercaseChar() + item.body.substring(1)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view =
                    layoutInflater.inflate(R.layout.comment_text, parent, false) as LinearLayout
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