package com.ravnnerdery.recyclechallenge.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ravnnerdery.recyclechallenge.database.tables.Comment
import com.ravnnerdery.recyclechallenge.database.tables.Post

@Dao
interface DatabaseDao {
    @Insert
    fun insertComment(comment: Comment)

    @Insert
    fun insertPost(post: Post)

    @Query("DELETE FROM comments_table")
    fun deleteComments()

    @Query("DELETE FROM posts_table")
    fun deletePosts()

    @Query("SELECT * from posts_table")
    fun getPosts(): LiveData<List<Post>>

    @Query("SELECT * from comments_table where post_id = :key")
    fun getComments(key: Long): Array<Comment>


}