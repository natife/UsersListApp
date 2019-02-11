package com.example.myapplication.application

import android.app.Application
import com.example.myapplication.di.appComponent
import org.koin.android.ext.android.startKoin

class GroupsApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponent)
    }
}