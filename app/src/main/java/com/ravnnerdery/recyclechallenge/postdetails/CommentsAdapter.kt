package com.ravnnerdery.recyclechallenge.postdetails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.tables.Comment


class CommentTextViewHolder(val commentTextView: TextView): RecyclerView.ViewHolder(commentTextView)

class CommentsAdapter: RecyclerView.Adapter<CommentTextViewHolder>() {
    var data = listOf<Comment>()

        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CommentTextViewHolder, position: Int) {
        val item = data[position]
        (item.name + " \n \n "+ item.email+ " \n \n " + item.body+ " \n \n " + "----------------------------------").also { holder.commentTextView.text = it }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentTextViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.comment_text, parent, false) as TextView
        return CommentTextViewHolder(view)
    }
}