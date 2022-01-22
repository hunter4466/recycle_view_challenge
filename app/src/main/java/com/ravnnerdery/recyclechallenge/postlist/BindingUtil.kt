package com.ravnnerdery.recyclechallenge.postlist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ravnnerdery.recyclechallenge.database.tables.Post
import kotlinx.android.synthetic.main.comment_text.view.*

@BindingAdapter("capitalizeTitle")
fun TextView.setCapitalizedTitle(item: Post?){
    item?.let{
        (item.title[0].uppercaseChar() + item.title.substring(1)).also { text = it }
    }
}
@BindingAdapter("capitalizeBody")
fun TextView.setCapitalizedBody(item: Post?){
    item?.let{
        (item.body[0].uppercaseChar() + item.body.substring(1)).also { text = it }
    }
}
