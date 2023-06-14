package com.example.travel_expenses

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("currencies/krw/{path}.json")
    fun get(
        @Path("path") path: String
    ):Call<apiresponse>

    companion object{
        private const val BASE_URL = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/"

        fun create():APIService {
            val gson : Gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build().create(APIService::class.java)
        }
    }
}