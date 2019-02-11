package com.example.myapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.ApiRepository
import com.example.myapplication.model.Group
import com.example.myapplication.util.ConnectionChecker
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class MainActivityViewModel (private val apiRepository: ApiRepository, private val connectionChecker: ConnectionChecker): ViewModel(){

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var groupsList: MutableLiveData<List<Group>> = MutableLiveData()

    fun loadGroupsList(){
        if (connectionChecker.isConnected()){
            compositeDisposable.add(apiRepository.loadGroups()
                .subscribeBy (
                    onNext = {
                        groupsList.value = it.groupsList
                    },
                    onError =  {
                    }
                ))
        }

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}