package com.example.fragrecview.ui.userdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragrecview.data.local.userdata.User
import com.example.fragrecview.data.local.userdata.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    fun fetchUsers(): LiveData<List<User>> = userDao.getAll()

    fun deleteUser(userID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.delete(userID)
        }
    }
}
