package com.android.machine3.repository

import com.android.machine3.models.User
import com.android.machine3.network.UserApiService

class UserRepository(private val userApiService: UserApiService) {

    suspend fun fetchUsers() : ArrayList<User> {
        return userApiService.fetchUsers()
    }
}