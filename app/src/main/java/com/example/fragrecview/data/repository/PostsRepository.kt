package com.example.fragrecview.data.repository

import androidx.lifecycle.LiveData
import com.example.fragrecview.data.local.userposts.PostsDao
import com.example.fragrecview.data.local.userposts.UserPostsList
import com.example.fragrecview.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepository(private val apiService: ApiService, private val postsDao: PostsDao) {
    suspend fun fetchPosts(): List<UserPostsList> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getPhotos()
            if (response.isSuccessful) {
                response.body()?.let { posts ->
                    postsDao.insertPosts(posts)
                    posts
                } ?: emptyList()
            } else {
                emptyList()
            }
        }
    }

    fun observePosts(): LiveData<List<UserPostsList>> = postsDao.showPost()

    suspend fun toggleFav(postId: Int) {
        withContext(Dispatchers.IO) {
            postsDao.toggleIsLiked(postId)
        }
    }
}