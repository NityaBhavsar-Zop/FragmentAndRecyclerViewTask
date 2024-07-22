package com.example.fragrecview.ui.userposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragrecview.R
import com.example.fragrecview.ui.userdetails.UserDetailsFragment
import com.example.fragrecview.ui.userposts.adapter.PostsAdapter
import com.example.fragrecview.ui.userposts.viewmodel.UserPostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPostsFragment : Fragment() {

    private lateinit var postAdapter: PostsAdapter
    private val showPostViewModel: UserPostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerViewPosts)
        recyclerView.layoutManager = LinearLayoutManager(context)
        postAdapter = PostsAdapter(requireContext()) { postId ->
            showPostViewModel.toggleFav(postId)
        }
        recyclerView.adapter = postAdapter
        showPostViewModel.observePost().observe(viewLifecycleOwner) { post ->
            postAdapter.updateData(post)
        }

        val goBackBtn: Button = view.findViewById(R.id.goBackButton)
        goBackBtn.setOnClickListener {
            loadFragment(UserDetailsFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
