package com.ravnnerdery.recyclechallenge.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey
import androidx.room.PrimaryKey;

@Entity(tableName = "comments_table")
class Comment (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "comment_name")
    var name: String,
    @ColumnInfo(name = "comment_email")
    var email: String,
    @ColumnInfo(name = "comment_body")
    var body: String,
    @ColumnInfo(name= "post_id")
    var postId: Long,
)
