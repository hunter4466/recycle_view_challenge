package com.ravnnerdery.recyclechallenge.postlist

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ravnnerdery.recyclechallenge.database.tables.Comment
import com.ravnnerdery.recyclechallenge.database.DatabaseDao
import com.ravnnerdery.recyclechallenge.database.tables.Post
import com.ravnnerdery.recyclechallenge.network.PostsApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Runnable
import java.util.*
private lateinit var runnable: Runnable

class PostlistViewModel(
    val database: DatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    private var handler = Handler()
    init {
        loadFromApiAndSetIntoDatabase()
        runAutoRefresh()
    }

    private fun runAutoRefresh() {
            runnable = Runnable {
                loadFromApiAndSetIntoDatabase()
                runAutoRefresh()
            }
            handler.postDelayed(runnable, 900000)
    }


    override fun onCleared() {
        handler.removeCallbacksAndMessages(null)
        clearDatabase()
        super.onCleared()
    }

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val allPostsFromDatabase = database.getPosts()
    private fun loadFromApiAndSetIntoDatabase() {
        PostsApi.retrofitService.getPosts().enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                response.body()?.forEach { elm ->
                    addPostToDatabase(elm.id, elm.title, elm.body)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                println(t.message)
            }
        })
        PostsApi.retrofitService.getComments().enqueue(object: Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                response.body()?.forEach { elm ->
                    addCommentToDatabase(elm.id,elm.name,elm.email,elm.body,elm.postId)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun addPostToDatabase(id: Long, title: String, body: String){
        uiScope.launch(Dispatchers.IO) {
            val newPost = Post(id, title, body)
            val sample = database.getSpecificPost(newPost.id)
            if(sample.isEmpty()) database.insertPost(newPost)
        }
    }

    private fun addCommentToDatabase(id: Long, name: String, email: String, body: String, postId: Long){
        uiScope.launch {
            val newComment = Comment(id, name, email, body, postId)
            insertSingleComment(newComment)
        }
    }

    private suspend fun insertSingleComment(comment: Comment) {
        return withContext(Dispatchers.IO){
            val sample = database.getSpecificComment(comment.id)
            if(sample.isEmpty())database.insertComment(comment)
        }
    }

    private fun clearDatabase(){
        uiScope.launch(Dispatchers.IO) {
            database.deletePosts()
            database.deleteComments()
        }
    }

    private val _navigateToDetails = MutableLiveData<Long?>()
    val navigateToDetails
        get() = _navigateToDetails

    fun onPostClicked(id: Long) {
        _navigateToDetails.value = id
    }

    fun onPostDetailsNavigated(){
        _navigateToDetails.value = null
    }

}