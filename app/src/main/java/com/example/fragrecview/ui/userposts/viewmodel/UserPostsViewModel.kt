package com.example.fragrecview.ui.userposts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragrecview.data.local.userposts.UserPostsList
import com.example.fragrecview.data.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPostsViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {
    init {
        downloadPost()
    }

    private fun downloadPost() {
        viewModelScope.launch {
            postsRepository.fetchPosts()
        }
    }

    fun observePost(): LiveData<List<UserPostsList>> = postsRepository.observePosts()
    fun toggleFav(postId: Int) {
        viewModelScope.launch {
            postsRepository.toggleFav(postId)
        }
    }
}
