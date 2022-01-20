package com.ravnnerdery.recyclechallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ravnnerdery.recyclechallenge.database.tables.Comment
import com.ravnnerdery.recyclechallenge.database.tables.Post

@Database(entities = [Post::class, Comment::class], version = 2, exportSchema = false)
abstract class PostsDatabase: RoomDatabase() {
    abstract val databaseDao: DatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: PostsDatabase? = null

        fun getInstance(context: Context) : PostsDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PostsDatabase::class.java,
                        "posts_history_database_fourteen"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}