package com.example.myapplication.di

import com.example.myapplication.api.ApiProvider
import com.example.myapplication.data.ApiRepository
import com.example.myapplication.data.ApiRepositoryImpl
import com.example.myapplication.util.ConnectionChecker
import com.example.myapplication.viewmodels.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val apiModule = module{
    factory { ApiProvider.provideApiInterface() }
}

val repositoryModule = module {
    single { ApiRepositoryImpl(get()) as ApiRepository}
}

val viewModelModule = module{
    viewModel { MainActivityViewModel(get(), get()) }
}

val utilModule = module{
    single{ ConnectionChecker(androidContext()) }
}

val appComponent: List<Module> = listOf(apiModule, repositoryModule, viewModelModule, utilModule)