package com.example.fragrecview.ui.userposts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fragrecview.data.repository.PostsRepository

class UserPostsViewModelFactory(private val postRepository: PostsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserPostsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserPostsViewModel(postRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}