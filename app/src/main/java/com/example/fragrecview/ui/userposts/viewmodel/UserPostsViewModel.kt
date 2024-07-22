package com.example.fragrecview.ui.userposts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fragrecview.data.local.userposts.UserPostsList
import com.example.fragrecview.data.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserPostsViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {
    init {
        downloadPost()
    }
    private fun downloadPost() {
        postsRepository.fetchPosts()
    }
    fun observePost(): LiveData<List<UserPostsList>> = postsRepository.observePosts()
    fun toggleFav(postId: Int) {
        postsRepository.toggleFav(postId)
    }
}
