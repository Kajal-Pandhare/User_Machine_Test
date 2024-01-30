package com.android.machine3.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.machine3.databinding.UserActivityBinding
import com.android.machine3.databinding.UserDetailsActivityBinding
import com.android.machine3.models.User

class UsersDetailsActivity  :AppCompatActivity() {

    private lateinit var binding: UserDetailsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserDetailsActivityBinding.inflate(layoutInflater)

         val message =  intent.getSerializableExtra("users")

       binding.users = message as User?

        setContentView(binding.root)
    }
}