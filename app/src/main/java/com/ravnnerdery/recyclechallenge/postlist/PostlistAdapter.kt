package com.ravnnerdery.recyclechallenge.postlist

import androidx.recyclerview.widget.RecyclerView

class PostlistAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<>()

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality
    }
}