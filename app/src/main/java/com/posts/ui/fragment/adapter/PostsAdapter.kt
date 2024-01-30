package com.posts.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.posts.R
import com.posts.base.adapters.CustomBaseAdapter
import com.posts.data.model.post.Post
import com.posts.databinding.RecyclerItemLayoutPostsBinding

class PostsAdapter(private val onItemClicked: (Post) -> Unit) :
    CustomBaseAdapter<Post, PostsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_layout_posts, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val binding = RecyclerItemLayoutPostsBinding.bind(itemView)
        fun bind(item: Post) {
            binding.tvBody.text = item.body
            binding.tvTitle.text = item.title
            itemView.setOnClickListener {
                onItemClicked(item)
            }
        }
    }
}