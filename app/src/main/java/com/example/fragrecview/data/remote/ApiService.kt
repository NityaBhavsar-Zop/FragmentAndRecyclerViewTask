package com.example.fragrecview.data.remote

import com.example.fragrecview.data.remote.response.UserDetailsList
import com.example.fragrecview.data.local.userposts.UserPostsList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<UserDetailsList>>

    @GET("photos")
    suspend fun getPhotos(): Response<List<UserPostsList>>
}