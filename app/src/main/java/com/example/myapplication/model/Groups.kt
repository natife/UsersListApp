package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Groups(

    @SerializedName("groups")
    val groupsList: List<Group>

)