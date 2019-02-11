package com.example.myapplication.util

import android.content.Context
import android.net.ConnectivityManager

class ConnectionChecker(private val context: Context){

    fun isConnected(): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}