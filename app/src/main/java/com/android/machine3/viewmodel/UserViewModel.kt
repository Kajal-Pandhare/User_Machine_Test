package com.android.machine3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.machine3.models.User
import com.android.machine3.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userRepository: UserRepository
) : ViewModel() {

    val userMutableLiveData = MutableLiveData<Boolean>()

    val users = ArrayList<User>()

    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.fetchUsers()

            withContext(Dispatchers.Main) {
                this@UserViewModel.users.addAll(user)
                userMutableLiveData.postValue(true)
            }
        }
    }
}