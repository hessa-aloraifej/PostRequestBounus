package com.example.postrequestbounus

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("/test")
    fun getUsers() : Call<ArrayList<PeopleItem>>

    @POST("custom-people/")
    fun addUsers(@Body userData:PeopleItem): Call<PeopleItem>
}