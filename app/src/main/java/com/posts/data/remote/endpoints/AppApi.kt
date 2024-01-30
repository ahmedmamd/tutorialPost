package com.posts.data.remote.endpoints

import com.posts.data.model.post.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {

    @GET("posts")
    suspend fun posts(): Response<ArrayList<Post>>

    @GET("posts/{id}")
    suspend fun userPosts(@Path("id") id: Int): Response<Post>


}