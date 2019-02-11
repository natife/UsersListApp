package com.example.myapplication.api


import com.example.myapplication.model.Groups
import com.example.myapplication.util.GET_GROUPS_URL
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface{

    @GET(GET_GROUPS_URL)
    fun loadGroups(): Observable<Groups>
}