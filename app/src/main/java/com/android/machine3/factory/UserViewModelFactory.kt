package com.android.machine3.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.machine3.repository.UserRepository
import com.android.machine3.viewmodel.UserViewModel

class UserViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}