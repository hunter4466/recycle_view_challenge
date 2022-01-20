package com.ravnnerdery.recyclechallenge.network

import com.squareup.moshi.Json

class PostsComment (
    @Json(name = "id") val commentId: String,
    @Json(name = "name") val commentName: String,
    @Json(name = "email") val commentEmail: String,
    @Json(name = "body") val commentBody: String,
    @Json(name = "postId") val commentPostId: String,
)
