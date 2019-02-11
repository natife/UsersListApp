package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("statusIcon")
    val statusIcon: String,

    @SerializedName("statusMessage")
    val statusMessage: String
){
    enum class Status(val status: String){
        OFFLINE ("offline"),
        ONLINE("online"),
        BUSY("busy"),
        AWAY("away"),
        CALLFORWARDING("callforwarding")
    }
}