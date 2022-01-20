package com.ravnnerdery.recyclechallenge.database

import androidx.room.*

@Dao
interface DatabaseDao {
    @Insert
    fun insertComment(comment: Comment) {
    }
    @Query("DELETE FROM comments_table")
    fun deleteComments()

    @Query("DELETE FROM posts_table")
    fun deletePosts()

    @Query("SELECT * from posts_table")
    fun getPosts(): Array<Post>

    @Query("SELECT * from comments_table where post_id = :key")
    fun getComments(key: Long): Array<Comment>

}