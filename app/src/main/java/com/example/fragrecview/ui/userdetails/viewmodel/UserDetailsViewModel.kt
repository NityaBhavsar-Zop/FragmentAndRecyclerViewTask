package com.example.fragrecview.ui.userdetails.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fragrecview.data.local.userdata.User
import com.example.fragrecview.data.local.userdata.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val providesUserDao: UserDao
) : ViewModel() {

    fun getUsers(): List<User> {
        return providesUserDao.getAll()
    }

    fun deleteUser(userID: String) {
        providesUserDao.delete(userID)
    }
}
