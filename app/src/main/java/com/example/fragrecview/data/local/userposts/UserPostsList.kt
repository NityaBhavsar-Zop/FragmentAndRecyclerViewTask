package com.example.fragrecview.data.local.userposts

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserPostsList(
    @PrimaryKey(autoGenerate = true)
    val postId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    val isLiked: Boolean = false
)