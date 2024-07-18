package com.example.fragrecview.ui.userdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fragrecview.data.local.userdata.UserDao

class UserDetailsViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserDetailsViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
