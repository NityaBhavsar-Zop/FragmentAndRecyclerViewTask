package com.example.fragrecview.data.remote

import com.example.fragrecview.data.remote.response.UserDetailsList
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<UserDetailsList>>
}