package com.posts.ui.fragment.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.posts.R
import com.posts.base.BaseFragment
import com.posts.data.model.NetworkState
import com.posts.data.model.post.Post
import com.posts.databinding.FragmentAllPostsBinding
import com.posts.ui.fragment.adapter.PostsAdapter
import kotlinx.coroutines.launch

class AllPostsFragment : BaseFragment(R.layout.fragment_all_posts) {

    // Declare view and objects
    lateinit var binding: FragmentAllPostsBinding
    private val postViewModel: PostsViewModel by activityViewModels()


    //Departments
    lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAllPostsBinding.inflate(layoutInflater)
        setupRecyclerAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllPostsBinding.inflate(layoutInflater, container, false)
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
        setupRecyclerUi()
    }


    // Set up event listeners for button clicks and other interactions
    private fun setupListeners() {
        viewLifecycleOwner.lifecycleScope.launch {
            postViewModel.postsFlow.collect {
                if (it is NetworkState.Success<*>) {
                    postsAdapter.setData(it.data as List<Post>)
                }
            }
        }
    }

    private fun setupRecyclerUi() {
        binding.ordersRecycler.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = postsAdapter
        }
    }

    private fun setupRecyclerAdapter() {
        postsAdapter = PostsAdapter {
            findNavController().navigate(R.id.userPosts,  bundleOf("id" to it.id))
        }
    }

    override fun onResume() {
        super.onResume()
      postViewModel.getPosts()
    }
}