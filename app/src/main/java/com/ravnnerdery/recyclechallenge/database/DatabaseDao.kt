package com.ravnnerdery.recyclechallenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DatabaseDao {
    @Insert
    fun insertComment(comment: Comment) {
    }
    @Query("SELECT * from posts_table")
    fun getPosts(): Array<Post>

    @Query("SELECT * from comments_table where post_id = :key")
    fun getComments(key: Long): Array<Comment>

}