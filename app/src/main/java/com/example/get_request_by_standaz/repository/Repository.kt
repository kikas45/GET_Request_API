package com.example.get_request_by_standaz.repository

import com.example.get_request_by_standaz.api.RetrofitInstance
import com.example.get_request_by_standaz.modal.Post
import retrofit2.Response

class Repository {
    suspend fun getPost():Response<Post>{
        return  RetrofitInstance.api.getPost()
    }
}