package com.ravnnerdery.recyclechallenge.postlist

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ravnnerdery.recyclechallenge.database.Comment
import com.ravnnerdery.recyclechallenge.database.DatabaseDao
import com.ravnnerdery.recyclechallenge.database.Post
import com.ravnnerdery.recyclechallenge.network.PostsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostlistViewModel() : ViewModel() {
    companion object{
        var database = DatabaseDao
    }
    var postList: List<Post> = listOf()
    init {
        println("activatedsadfasdfasdfasdfasdfasdfasdf")
      //  postList = database.getPosts()
       loadFromApi()
    }

    private fun loadFromApi() {
        PostsApi.retrofitService.getPosts().enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                for(elm in response.body()!!){
                    println(elm.body)
                    println(elm.id)
                    println(elm.title)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                println(t.message)
            }
        })
        PostsApi.retrofitService.getComments().enqueue(object: Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                for(elm in response.body()!!){
                    println(elm.postId)
                    println(elm.id)
                    println(elm.name)
                    println(elm.email)
                    println(elm.body)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                println(t.message)
            }
        })
    }
}