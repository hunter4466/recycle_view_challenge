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

    @Query("SELECT * FROM posts_table WHERE id = :key")
    fun getSpecificPost(key: Long): Array<Post>

    @Query("SELECT * FROM comments_table WHERE id = :key")
    fun getSpecificComment(key: Long): Array<Comment>

    @Query("DELETE FROM comments_table")
    fun deleteComments()

    @Query("DELETE FROM posts_table")
    fun deletePosts()

    @Query("SELECT * from posts_table")
    fun getPosts(): LiveData<List<Post>>?

    @Query("SELECT * from comments_table where post_id = :key")
    fun getComments(key: Long): LiveData<List<Comment>>?

}