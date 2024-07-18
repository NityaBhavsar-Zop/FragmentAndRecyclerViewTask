package com.example.fragrecview.ui.userposts.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragrecview.R
import com.example.fragrecview.data.local.userposts.UserPostsList

class PostsAdapter(
    private val context: Context,
    private val onFavClick: (Int) -> Unit,
) : RecyclerView.Adapter<PostsAdapter.UserViewHolder>() {
    private var posts = listOf<UserPostsList>()

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userPostTitle: TextView = view.findViewById(R.id.postTitleTextView)
        private val userPostImage: ImageView = view.findViewById(R.id.postImageView)
        val heartButton: ImageView = view.findViewById(R.id.heartButton)

        fun setData(post: UserPostsList) {
            userPostTitle.text = post.title
            Glide.with(userPostImage.context)
                .load(post.thumbnailUrl)
                .into(userPostImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_posts, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setData(posts[position])
        holder.heartButton.setImageDrawable(ContextCompat.getDrawable(context, if(posts[position].isLiked) R.drawable.ic_heart_red else  R.drawable.ic_heart ))
        holder.heartButton.setOnClickListener {
            onFavClick(posts[position].id)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<UserPostsList>) {
        posts = data
        notifyDataSetChanged()
    }
}
