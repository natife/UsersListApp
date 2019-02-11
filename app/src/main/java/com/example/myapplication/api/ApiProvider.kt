package com.example.myapplication.api

import com.example.myapplication.util.BASE_URL
import com.example.myapplication.util.DEFAULT_TIMEOUT
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiProvider{
    companion object {
        fun provideApiInterface(): ApiInterface {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient().newBuilder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            retrofitBuilder.client(client)

            return retrofitBuilder.build().create(ApiInterface::class.java)

        }
    }
}