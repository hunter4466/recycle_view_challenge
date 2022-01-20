package com.ravnnerdery.recyclechallenge.postlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ravnnerdery.recyclechallenge.R
import com.ravnnerdery.recyclechallenge.database.tables.Post

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class PostsAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Post>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        (item.title + " \n \n " + item.body).also { holder.textView.text = it }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }
}