package com.example.fragrecview.data.local.userposts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostsDao {

    @Insert
    suspend fun insertPosts(post: List<UserPostsList>)

    @Query("SELECT * from UserPostsList")
    fun showPost(): LiveData<List<UserPostsList>>

    @Query("UPDATE UserPostsList SET isLiked = NOT isLiked WHERE postId = :postId")
    fun toggleIsLiked(postId: Int)
}