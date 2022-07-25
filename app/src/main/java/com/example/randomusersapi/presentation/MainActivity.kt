package com.example.randomusersapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.randomusersapi.R
import com.example.randomusersapi.databinding.ActivityMainBinding
import com.example.randomusersapi.presentation.userdetails.UserDetailsFragment
import com.example.randomusersapi.presentation.userlist.UserListFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, UserListFragment())
                .commit()
        }
    }
}