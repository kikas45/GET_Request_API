package com.example.get_request_by_standaz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.get_request_by_standaz.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var userId:TextView
    private lateinit var id:TextView
    private lateinit var title:TextView
    private lateinit var body_th:TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userId = findViewById(R.id.userId)
        id = findViewById(R.id.id)
        title = findViewById(R.id.title)
        body_th = findViewById(R.id.body_th)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()


    /*    viewModel.myResponse.observe(this, Observer {

            userId.text = it.userId.toString()
            id.text = it.id.toString()
            title.text = it.title.toString()
            body.text = it.body.toString()

        })  */

        // che3ck for error 404
        viewModel.myResponse.observe(this, Observer { response ->


            if (response.isSuccessful){
                userId.text = response.body()!!.userId.toString()
                id.text = response.body()!!.id.toString()
                title.text = response.body()!!.title.toString()
                body_th.text = response.body()!!.body.toString()
            }else{
                title.text =  " " + response.code().toString()
                body_th.text =  " " + response.errorBody()!!.toString()
            }

        })

    }
}