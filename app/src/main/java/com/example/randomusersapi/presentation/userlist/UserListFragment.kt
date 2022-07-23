package com.example.randomusersapi.presentation.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.randomusersapi.R
import com.example.randomusersapi.databinding.FragmentUserListBinding
import com.example.randomusersapi.domain.User
import com.example.randomusersapi.presentation.ViewModelFactory
import com.example.randomusersapi.presentation.userdetails.UserDetailsFragment
import com.example.randomusersapi.presentation.userlist.adapter.UserListAdapter

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    private val viewModel by lazy {
        val viewModelFactory = ViewModelFactory(activity?.application!!)
        ViewModelProvider(this, viewModelFactory)[UserListViewModel::class.java]
    }

    private val adapter by lazy {
        UserListAdapter(::onItemClickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.VISIBLE

        setupAdapter()
        setupObserves()
    }

    private fun setupAdapter() {
        binding.rvUserList.adapter = adapter
    }

    private fun setupObserves() {
        viewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.errorLoading.observe(viewLifecycleOwner) {
            with(binding) {
                if (it) {
                    progressBar.visibility = View.GONE
                    tvErrorLoading.visibility = View.VISIBLE
                    btnTryAgain.visibility = View.VISIBLE

                    btnTryAgain.setOnClickListener {
                        progressBar.visibility = View.VISIBLE
                        tvErrorLoading.visibility = View.GONE
                        btnTryAgain.visibility = View.GONE
                        viewModel.getUserList()
                    }
                } else {
                    progressBar.visibility = View.GONE
                    tvErrorLoading.visibility = View.GONE
                    btnTryAgain.visibility = View.GONE
                }
            }
        }
    }

    private fun onItemClickListener(user: User) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container, UserDetailsFragment.newInstance(user.uuid))
            .addToBackStack(null)
            .commit()
    }
}