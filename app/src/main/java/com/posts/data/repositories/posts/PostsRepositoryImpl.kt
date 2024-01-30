package  com.posts.data.repositories.posts

import com.posts.data.remote.endpoints.AppApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(private val appApi: AppApi) : PostsRepository {

    override fun posts()= flow { emit(appApi.posts()) }

    override fun userPost(id: Int)= flow { emit(appApi.userPosts(id)) }



}