package com.example.get_request_by_standaz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.get_request_by_standaz.adapter.MyAdapter
import com.example.get_request_by_standaz.repository.Repository
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private val myAdapter by lazy {
        MyAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            setupRecyclerview()
        }catch (ignored:Exception){}



        try {
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

            viewModel.getCustomPosts(2, "id", "desc")

            viewModel.myCustomPosts.observe(this, Observer { response ->

                try {

                    if (response.isSuccessful){
                        response.body()?.let { myAdapter.setData(it) }
                    }

                }catch (ignored:Exception){}

            })
        }catch (ignored:Exception){}


    }

    private fun setupRecyclerview() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}