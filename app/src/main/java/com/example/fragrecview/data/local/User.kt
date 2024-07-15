package com.example.fragrecview.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userID: String,
    @ColumnInfo(name = "Name") val userName: String?,
    @ColumnInfo(name = "Phone") val userPhone: String?
)