package com.example.get_request_by_standaz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.get_request_by_standaz.modal.Post
import com.example.get_request_by_standaz.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

//  is for handling error 404 , i.e no url found
// wrapping post with Response will solve the problem

class MainViewModel(private val repository: Repository): ViewModel() {

    var myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    var myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    var myCustomPosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    var myCustomPosts2: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun pushPost(post: Post) {
        viewModelScope.launch {
           try {
               val response = repository.pushPost(post)
               if (response.isSuccessful){
                   myResponse.value = response
               }
           }catch (ignored:Exception){}
        }
    }

    fun pushPost2(userId: Int, id: Int, title: String, body: String) {
        viewModelScope.launch {
            val response = repository.pushPost2(userId, id, title, body)
            myResponse.value = response
        }
    }

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
    fun getPost2(number: Int) {
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }
    fun getCustomPosts(userId: Int, sort: String, order: String) {

        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.getCustomPosts(userId, sort, order)
                if (response.isSuccessful){
                    myCustomPosts.value = response
                }
            }
        }catch (ignored:Exception){}
    }

    fun getCustomPosts2(userId: Int, options: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getCustomPosts2(userId, options)
            myCustomPosts2.value = response
        }
    }

}