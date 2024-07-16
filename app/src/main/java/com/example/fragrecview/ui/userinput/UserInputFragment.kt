package com.example.fragrecview.ui.userinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragrecview.R
import com.example.fragrecview.ui.userdetails.UserDetailsFragment
import com.example.fragrecview.ui.userinput.viewmodel.UserInputViewModel

class UserInputFragment : Fragment() {

    private lateinit var userInputViewModel: UserInputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_input, container, false)
        userInputViewModel = ViewModelProvider(this)[UserInputViewModel::class.java]

        val addUserBtn: Button = view.findViewById(R.id.addUser)
        addUserBtn.setOnClickListener {
            userInputViewModel.getInput(requireContext())
            loadFragment(UserDetailsFragment())
        }

        return view
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
