package com.posts.ui.fragment.posts

import androidx.lifecycle.viewModelScope
import com.posts.base.BaseViewModell
import com.posts.data.model.NetworkState
import com.posts.domin.posts.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val homeUseCase: PostsUseCase) : BaseViewModell() {


    private val _userPostFlow = MutableSharedFlow<NetworkState>()
    val userPostFlow get() = _userPostFlow.asSharedFlow()

    fun userPost(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            executeSharedFlow(_userPostFlow, homeUseCase.getUserPost(id = id))
        }
    }


    private val _postsFlow = MutableSharedFlow<NetworkState>()
    val postsFlow get() = _postsFlow.asSharedFlow()

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            executeSharedFlow(_postsFlow, homeUseCase.posts())
        }
    }


}