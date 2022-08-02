package com.example.randomusersapi.presentation.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.randomusersapi.R
import com.example.randomusersapi.databinding.FragmentUserListBinding
import com.example.randomusersapi.domain.User
import com.example.randomusersapi.presentation.ViewModelFactory
import com.example.randomusersapi.presentation.userdetails.UserDetailsFragment
import com.example.randomusersapi.presentation.userlist.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    private val adapter by lazy {
        UserListAdapter(::onItemClickListener, ::loadUserData)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserListViewModel::class.java]
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