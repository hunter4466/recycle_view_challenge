package com.ravnnerdery.recyclechallenge.postlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ravnnerdery.recyclechallenge.database.tables.Comment
import com.ravnnerdery.recyclechallenge.database.DatabaseDao
import com.ravnnerdery.recyclechallenge.database.tables.Post
import com.ravnnerdery.recyclechallenge.network.PostsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostlistViewModel(
    val database: DatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    init {
        loadFromApiAndSetIntoDatabase()
    }
    var postList: List<Post> = listOf()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private fun loadFromApiAndSetIntoDatabase() {
        PostsApi.retrofitService.getPosts().enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                for(elm in response.body()!!){
                    addPostToDatabase(elm.id, elm.title, elm.body)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                println(t.message)
            }
        })
        PostsApi.retrofitService.getComments().enqueue(object: Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                for(elm in response.body()!!){
                    addCommentToDatabase(elm.id,elm.name,elm.email,elm.body,elm.postId)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun addPostToDatabase(id: Long, title: String, body: String){
        uiScope.launch {
            val newPost = Post(id, title, body)
            database.insertPost(newPost)
        }
    }
    private fun addCommentToDatabase(id: Long, name: String, email: String, body: String, postId: Long){
        uiScope.launch {
            val newComment = Comment(id, name, email, body, postId)
            database.insertComment(newComment)
        }
    }

    override fun onCleared() {
        super.onCleared()
        database.deletePosts()
        database.deleteComments()
    }
}