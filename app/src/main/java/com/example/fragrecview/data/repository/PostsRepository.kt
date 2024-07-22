package com.example.fragrecview.data.repository

import androidx.lifecycle.LiveData
import com.example.fragrecview.data.local.userposts.PostsDao
import com.example.fragrecview.data.local.userposts.UserPostsList
import com.example.fragrecview.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsRepository(private val apiService: ApiService, private val postsDao: PostsDao) {
    fun fetchPosts() {
        val posts = apiService.getPhotos()
        posts.enqueue(object : Callback<List<UserPostsList>> {
            override fun onResponse(
                call: Call<List<UserPostsList>>,
                response: Response<List<UserPostsList>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        postsDao.insertPosts(posts)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserPostsList>>, t: Throwable) {}
        })
    }

    fun observePosts(): LiveData<List<UserPostsList>> = postsDao.showPost()
    fun toggleFav(postId: Int) {
        postsDao.toggleIsLiked(postId)
    }
}