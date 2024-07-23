package com.example.fragrecview.ui.userdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragrecview.data.local.userdata.User
import com.example.fragrecview.data.local.userdata.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    fun fetchUsers() {
        viewModelScope.launch {
            _users.value = userDao.getAll()
        }
    }

    fun deleteUser(userID: String) {
        viewModelScope.launch {
            userDao.delete(userID)
            fetchUsers()
        }
    }
}
