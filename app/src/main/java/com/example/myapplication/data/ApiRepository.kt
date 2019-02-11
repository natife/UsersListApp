package com.example.myapplication.data


import com.example.myapplication.api.ApiInterface
import com.example.myapplication.model.Groups
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ApiRepository{

    fun loadGroups(): Observable<Groups>
}

class ApiRepositoryImpl(private val apiInterface: ApiInterface): ApiRepository{

    override fun loadGroups(): Observable<Groups>{
        return apiInterface.loadGroups()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}