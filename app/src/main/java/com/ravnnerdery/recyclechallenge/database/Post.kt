package com.ravnnerdery.recyclechallenge.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
class Post (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "post_title")
    var title: String,
    @ColumnInfo(name = "post_body")
    var body: String
)