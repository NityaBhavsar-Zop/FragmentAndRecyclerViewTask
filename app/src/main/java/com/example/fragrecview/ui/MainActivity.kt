package com.example.fragrecview.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.fragrecview.R
import com.example.fragrecview.data.remote.ApiService
import com.example.fragrecview.data.remote.response.UserDetailsList
import com.example.fragrecview.ui.userdetails.UserDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(UserDetailsFragment())
        lifecycleScope.launch {
            makeApiCall()
        }
    }

    fun loadFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    private suspend fun makeApiCall() {
        withContext(Dispatchers.IO) {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                val result: List<UserDetailsList>? = response.body()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "${result?.getOrNull(1)?.name}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Failure: Failed to fetch data",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
