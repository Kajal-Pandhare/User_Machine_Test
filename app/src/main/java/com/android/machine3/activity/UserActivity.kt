package com.android.machine3.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Address
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.machine3.R
import com.android.machine3.adapter.UserAdapter
import com.android.machine3.databinding.UserActivityBinding
import com.android.machine3.factory.UserViewModelFactory
import com.android.machine3.models.User
import com.android.machine3.network.UserApiService
import com.android.machine3.repository.UserRepository
import com.android.machine3.viewmodel.UserViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var binding: UserActivityBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.user_activity)
        binding = UserActivityBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initObserver()
        initAdapter()
        initListeners()

        userViewModel.fetchUsers()
        setContentView(binding.root)
    }
    private fun initListeners() {
        binding.recyclerUsers.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        userViewModel.fetchUsers()
                    }
                }
            })
        userAdapter.onuserClickListener =
            object : UserAdapter.OnuserClickListener{
                override fun onUserClick(user: User, position: Int, userAdapter: UserAdapter) {
                    Log.e("tag",user.toString())
                    showDetailsFragment(user)

                }
            }
    }
    private fun showDetailsFragment(user: User){

        val intent =  Intent(this@UserActivity,UsersDetailsActivity::class.java)
        intent.putExtra("users",user)
        startActivityForResult(intent,1)

    }
    private fun initAdapter(){
        userAdapter = UserAdapter(userViewModel.users)
        binding.recyclerUsers.adapter = userAdapter
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(){
        userViewModel.userMutableLiveData.observe(
            this
        ){
            if (it){
                userAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initViewModels(){
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(
                UserRepository(
                    UserApiService.getInstance()
                )
            )
        )[UserViewModel::class.java]
    }
    private fun initViews(){
        binding.recyclerUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

    }
}