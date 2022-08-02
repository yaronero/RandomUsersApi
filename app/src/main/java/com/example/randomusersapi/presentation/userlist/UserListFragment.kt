package com.example.randomusersapi.presentation.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.randomusersapi.R
import com.example.randomusersapi.databinding.FragmentUserListBinding
import com.example.randomusersapi.domain.User
import com.example.randomusersapi.presentation.userdetails.UserDetailsFragment
import com.example.randomusersapi.presentation.userlist.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    private val viewModel by viewModel<UserListViewModel>()

    private val adapter by lazy {
        UserListAdapter(::onItemClickListener, ::loadUserData)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        retainInstance = true
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadProgress.isVisible = true

        if (viewModel.userList.value == null) {
            viewModel.getUserList()
        }
        setupAdapter()
        setupObserves()
    }

    private fun loadUserData() {
        viewModel.getUserList()
        binding.loadProgress.isVisible = true
    }

    private fun setupAdapter() {
        binding.rvUserList.adapter = adapter
    }

    private fun setupObserves() {
        viewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.loadProgress.isVisible = false
        }

        viewModel.errorLoading.observe(viewLifecycleOwner) {
            with(binding) {
                if (it) {
                    loadProgress.isVisible = false
                    tvErrorLoading.isVisible = true
                    btnTryAgain.isVisible = true

                    btnTryAgain.setOnClickListener {
                        loadProgress.isVisible = true
                        tvErrorLoading.isVisible = false
                        btnTryAgain.isVisible = false
                        viewModel.getUserList()
                    }
                } else {
                    loadProgress.isVisible = false
                    tvErrorLoading.isVisible = false
                    btnTryAgain.isVisible = false
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