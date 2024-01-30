package com.android.machine3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.machine3.R
import com.android.machine3.databinding.UserViewBinding
import com.android.machine3.models.User
import com.bumptech.glide.Glide

class UserAdapter(private val users  :ArrayList<User>):
RecyclerView.Adapter<UserAdapter.UserViewModelHolder>() {

    interface OnuserClickListener{
        fun onUserClick(user: User,position: Int,userAdapter: UserAdapter)
    }
    var onuserClickListener : OnuserClickListener? = null

    inner class UserViewModelHolder(view: View): RecyclerView.ViewHolder(view){
        val binding : UserViewBinding

        init {
           binding = UserViewBinding.bind(view)

            binding.root.setOnClickListener {
                onuserClickListener?.onUserClick(
                    users[adapterPosition],
                    adapterPosition,
                    this@UserAdapter
                )
            }
        }
    }

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModelHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view,null)

        return UserViewModelHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewModelHolder, position: Int) {
        val user = users[position]

        holder.binding.txtUserName.text = user.username
        holder.binding.txtFirstName.text = user.name.firstname
        holder.binding.txtLastName.text = user.name.lastname

        /*Glide.with(holder.itemView)
            .load(users[position].avatar)
            .circleCrop()
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.image)*/
    }
}
