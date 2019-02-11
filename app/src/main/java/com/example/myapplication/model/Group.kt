package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Group(

    @SerializedName("groupName")
    val groupName: String,

    @SerializedName("people")
    val peoplesList: List<User>
)