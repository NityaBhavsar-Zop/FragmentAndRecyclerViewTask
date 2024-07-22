package com.example.fragrecview.ui.userinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        val view = inflater.inflate(R.layout.fragment_user_input, container, false)

        val addUserBtn: Button = view.findViewById(R.id.addUser)
        addUserBtn.setOnClickListener {
            userInputViewModel.getInput(view)
            val userDetailsFragment = UserDetailsFragment()
            activity?.let { mainActivity ->
                if (mainActivity is MainActivity) {
                    mainActivity.loadFragment(userDetailsFragment)
                }
            }
        }
        return view
    }
}
