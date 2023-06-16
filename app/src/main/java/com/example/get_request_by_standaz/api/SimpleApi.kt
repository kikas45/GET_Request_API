package com.example.get_request_by_standaz.api

import com.example.get_request_by_standaz.modal.Post
import retrofit2.Response
import retrofit2.http.GET

/*interface SimpleApi {
    @GET("/posts/1")
    suspend fun getPost():Post
}*/

//  is for handling error 404 , i.e no url found
// wrapping post with Response will solve the problem

interface SimpleApi {
    @GET("/posts/1")  /// if the url is not correct we get an HTTP error message 404
    suspend fun getPost():Response<Post>
}