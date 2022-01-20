package com.ravnnerdery.recyclechallenge.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PostsApiService {
    @GET("posts")
    fun getPosts(): Call<String>

    @GET("comments")
    fun getComments(): Call<String>
}
object PostsApi {
    val retrofitService : PostsApiService by lazy {
        retrofit.create(PostsApiService::class.java)
    }
}