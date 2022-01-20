package com.ravnnerdery.recyclechallenge.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
class Post(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    @ColumnInfo(name = "post_title")
    var title: String,
    @ColumnInfo(name = "post_body")
    var body: String
)