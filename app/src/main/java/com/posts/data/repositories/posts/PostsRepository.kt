package com.posts.data.repositories.posts

import com.posts.data.model.post.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PostsRepository {

    fun posts(): Flow<Response<ArrayList<Post>>>
   fun userPost(id:Int): Flow<Response<Post>>

}