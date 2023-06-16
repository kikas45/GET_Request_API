package com.example.get_request_by_standaz.modal

// filed must be same with Json field type
// else use @serialized data object
// if we var userId:Int,  is changed to var myUserId:Int, then apply SerializedName
//@SerializedName("userId")

data class Post(

    var userId: Int,
    var id: Int,
    var title: String,
    var body: String,

    )
