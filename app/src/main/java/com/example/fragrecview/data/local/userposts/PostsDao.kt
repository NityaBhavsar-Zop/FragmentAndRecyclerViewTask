package com.example.fragrecview.data.local.userposts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PostsDao {
    @Insert
    fun insertPost(posts: UserPostsList)

    @Insert
    fun insertPosts(post: List<UserPostsList>)

    @Query("SELECT * from UserPostsList")
    fun showPost(): LiveData<List<UserPostsList>>

    @Update
    fun updatePost(posts: UserPostsList)

    @Query("UPDATE UserPostsList SET isLiked = NOT isLiked WHERE postId = :postId")
    fun toggleIsLiked(postId: Int)
}