package com.example.fragrecview.ui.userdetails.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fragrecview.data.local.userdata.User
import com.example.fragrecview.data.local.userdata.UserDao

class UserDetailsViewModel(private val userDao: UserDao) : ViewModel() {
    fun getUsers(): List<User> {
        return userDao.getAll()
    }

    fun deleteUser(userID: String) {
        userDao.delete(userID)
    }
}
