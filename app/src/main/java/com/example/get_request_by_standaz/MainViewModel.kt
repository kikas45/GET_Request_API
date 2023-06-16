package com.example.get_request_by_standaz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.get_request_by_standaz.modal.Post
import com.example.get_request_by_standaz.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

//  is for handling error 404 , i.e no url found
// wrapping post with Response will solve the problem

class MainViewModel(private  val  repository: Repository) : ViewModel() {
    val myResponse = MutableLiveData<Response<Post>>()
    fun  getPost(){
        viewModelScope.launch {

            val response = repository.getPost()
            myResponse.value = response

        }
    }
}