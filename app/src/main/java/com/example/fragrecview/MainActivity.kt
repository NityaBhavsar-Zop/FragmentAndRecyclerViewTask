package com.example.fragrecview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fragrecview.data.remote.GetRetrofitInstance
import com.example.fragrecview.data.remote.response.UserDetailsList
import com.example.fragrecview.ui.userdetails.UserDetailsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(UserDetailsFragment())
        makeApiCall()
    }

     fun loadFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun makeApiCall() {
        val apiService = GetRetrofitInstance().getRetrofitInstance()
        apiService.getUsers().enqueue(object : Callback<List<UserDetailsList>> {
            override fun onResponse(
                call: Call<List<UserDetailsList>>,
                response: Response<List<UserDetailsList>>
            ) {
                if (response.isSuccessful) {
                    val result: List<UserDetailsList>? = response.body()
                    Toast.makeText(this@MainActivity, "${result?.getOrNull(1)?.name}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<UserDetailsList>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
