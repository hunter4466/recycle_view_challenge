package com.ravnnerdery.recyclechallenge.network

import com.squareup.moshi.Json

data class PostsPost (
@Json(name = "id") val postId: String,
@Json(name = "title") val postTitle: String,
@Json(name = "body") val postBody: String,
)