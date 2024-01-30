package com.posts.domin.posts

import com.posts.commons.extention.transformResponseData
import com.posts.data.model.post.Post
import com.posts.data.repositories.posts.PostsRepository
import javax.inject.Inject

class PostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {

    suspend fun posts() =
        postsRepository.posts().transformResponseData<ArrayList<Post>> { emit(it) }

    suspend fun getUserPost(id: Int) =
        postsRepository.userPost(id)
            .transformResponseData<Post> {emit(it) }

}