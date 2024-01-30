package com.posts.di


import com.posts.data.repositories.posts.PostsRepository
import com.posts.data.repositories.posts.PostsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PostsRepoModuleProvider {
    @Binds
    abstract fun provideAuthModule(repository: PostsRepositoryImpl): PostsRepository
}