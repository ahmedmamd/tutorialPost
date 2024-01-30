package com.posts.ui.fragment.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.posts.R
import com.posts.base.BaseFragment
import com.posts.data.model.NetworkState
import com.posts.data.model.post.Post
import com.posts.databinding.FragmentPostBinding
import kotlinx.coroutines.launch

class UserPostFragment : BaseFragment(R.layout.fragment_post) {

    // Declare view and objects
    lateinit var binding: FragmentPostBinding
    private val postViewModel: PostsViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPostBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
    }


    // Set up the UI components
    private fun setupUI() {
        // Set up any UI-related configurations here
        arguments?.getInt("id")?.let {
            postViewModel.userPost(it)
        }
    }


    // Set up event listeners for button clicks and other interactions
    private fun setupListeners() {
        viewLifecycleOwner.lifecycleScope.launch {
            postViewModel.userPostFlow.collect {
                if (it is NetworkState.Success<*>) {
                    setupUi(item = it.data as Post)
                }
            }
        }
    }

    private fun setupUi(item:Post) {
        binding.apply {
            item?.let {
                tvTitle.text =it.title
                tvBody.text = it.body
            }
        }
    }

}