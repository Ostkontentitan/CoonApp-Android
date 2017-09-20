package com.jodelapp.data.models

/**
 * Created by ottek on 20.09.2017.
 */
data class UserProfile(var id: Int, var name: String, var email: String, var address: Address, var phone: String, var website: String, var company: Company)
data class Address(var street: String, var suite: String, var city: String, var zipcode: String, var geo: Geo)
data class Geo(var lat: String, var lng: String)
data class Company(var name: String, var catchPhrase: String, var bs: String)