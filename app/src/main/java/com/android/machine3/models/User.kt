package com.android.machine3.models

import android.location.Address
import java.io.Serializable

data class User(
    val address: Address,
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val phone: String,
    val __v: String
) : Serializable {
    data class Address(
        val geolocation: Geolocation,
        val city: String,
        val street: String,
        val number: String,
        val zipcode: String
    ) : Serializable

    data class Geolocation(
        val lat: String,
        val long: String
    ) : Serializable

    data class Name(
        val firstname: String,
        val lastname: String
    ) : Serializable
}
