package com.example.randomusersapi.presentation.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.randomusersapi.R
import com.example.randomusersapi.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[UserDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        val user = viewModel.getUserByUuid(arguments?.getString(USER_UUID)!!)

        with(binding) {
            Glide
                .with(view)
                .load(user.imageUrl)
                .into(binding.userImage)
            userFirstName.text = getString(R.string.user_first_name, user.firstName)
            userGender.text = getString(R.string.user_gender, user.gender)
            userAge.text = getString(R.string.user_age, user.age.toString())
            userEmail.text = getString(R.string.user_email, user.email)
        }
    }

    companion object {
        private const val USER_UUID = "USER_UUID"

        fun newInstance(uuid: String): UserDetailsFragment {
            return UserDetailsFragment().apply {
                arguments = bundleOf(USER_UUID to uuid)
            }
        }
    }
}