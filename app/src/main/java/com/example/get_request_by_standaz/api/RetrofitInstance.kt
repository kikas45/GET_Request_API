package com.example.get_request_by_standaz.api

import com.example.get_request_by_standaz.utls.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// we use the object key word because we want to create an instance
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}