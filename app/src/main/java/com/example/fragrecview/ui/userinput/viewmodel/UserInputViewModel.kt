package com.example.fragrecview.ui.userinput.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragrecview.data.local.userdata.User
import com.example.fragrecview.data.local.userdata.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInputViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    fun isValidInput(userId: String, userName: String, userPhone: String): Boolean {
        return userId.isNotBlank() && userName.isNotBlank() && userPhone.isNotBlank()
    }

    fun addUser(userId: String, userName: String, userPhone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(userId, userName, userPhone)
            userDao.insert(user)
        }
    }
}
