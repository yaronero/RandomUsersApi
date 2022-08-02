package com.example.randomusersapi.presentation.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.randomusersapi.R
import com.example.randomusersapi.databinding.FragmentUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding

    private val viewModel: UserDetailsViewModel by viewModels()

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
        val userUuid = arguments?.getString(USER_UUID)!!
        viewModel.getUserByUuid(userUuid)
        setupUserObserver(view)
    }

    private fun setupUserObserver(view: View) {
        viewModel.userInfo.observe(viewLifecycleOwner) {
            with(binding) {
                Glide
                    .with(view)
                    .load(it.imageUrl)
                    .into(binding.userImage)
                userFirstName.text = getString(R.string.user_first_name, it.firstName)
                userGender.text = getString(R.string.user_gender, it.gender)
                userAge.text = getString(R.string.user_age, it.age.toString())
                userEmail.text = getString(R.string.user_email, it.email)
            }
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