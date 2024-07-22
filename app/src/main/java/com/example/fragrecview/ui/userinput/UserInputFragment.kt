package com.example.fragrecview.ui.userinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fragrecview.R
import com.example.fragrecview.ui.MainActivity
import com.example.fragrecview.ui.userdetails.UserDetailsFragment
import com.example.fragrecview.ui.userinput.viewmodel.UserInputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInputFragment : Fragment() {

    private val userInputViewModel: UserInputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userID: EditText = view.findViewById(R.id.userId)
        val userName: EditText = view.findViewById(R.id.userName)
        val userPhone: EditText = view.findViewById(R.id.userPhone)

        val addUserBtn: Button = view.findViewById(R.id.addUser)
        addUserBtn.setOnClickListener {
            val id = userID.text.toString()
            val name = userName.text.toString()
            val phone = userPhone.text.toString()
            if(userInputViewModel.isValidInput(id, name, phone)) {
                userInputViewModel.addUser(id, name, phone)
                userID.text.clear()
                userName.text.clear()
                userPhone.text.clear()
                Toast.makeText(requireContext(), "User added successfully", Toast.LENGTH_LONG).show()
                activity?.let { mainActivity ->
                    val userDetailsFragment = UserDetailsFragment()
                    if (mainActivity is MainActivity) {
                        mainActivity.loadFragment(userDetailsFragment)
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_LONG).show()
            }
        }
    }
}
