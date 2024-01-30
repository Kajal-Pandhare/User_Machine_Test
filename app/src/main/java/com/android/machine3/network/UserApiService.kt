package com.android.machine3.network

import com.android.machine3.models.User
import com.android.machine3.models.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApiService {

@GET("users")

    suspend fun fetchUsers() : ArrayList<User>

    companion object {
        private var userApiService: UserApiService? = null

        fun getInstance(): UserApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            val client =  OkHttpClient.Builder().addInterceptor(interceptor).build();

            if (userApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                userApiService = retrofit.create(UserApiService::class.java)
            }
            return userApiService!!

        }
    }
    }