package com.example.fragrecview.ui.userposts.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fragrecview.data.remote.GetRetrofitInstance
import com.example.fragrecview.data.remote.response.UserPostsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPostsViewModel : ViewModel() {
    fun getPosts(callback: (List<UserPostsList>) -> Unit) {
        val apiService = GetRetrofitInstance().getRetrofitInstance()
        apiService.getPhotos().enqueue(object : Callback<List<UserPostsList>> {
            override fun onResponse(
                call: Call<List<UserPostsList>>,
                response: Response<List<UserPostsList>>
            ) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    Log.d("UserPostsViewModel", "API Response: ${posts.size} posts")
                    callback(posts)
                } else {
                    Log.e("UserPostsViewModel", "API Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserPostsList>>, t: Throwable) {
                Log.e("UserPostsViewModel", "API Failure", t)
            }
        })
    }
}
