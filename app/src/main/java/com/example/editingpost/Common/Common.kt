package com.example.editingpost.Common

import com.example.editingpost.Interface.RetrofitServices
import com.example.editingpost.Retrofit.RetrofitClient


object Common {
    private val BASE_URL = "http://test.flcd.ru/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}