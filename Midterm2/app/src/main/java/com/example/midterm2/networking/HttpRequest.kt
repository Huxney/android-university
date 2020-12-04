package com.example.midterm2.networking

import android.util.Log.d
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*


object HttpRequest {
    const val PATH = "0798cb28-d1ba-4c30-9c64-112a372d1d7d"

    private var retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://run.mocky.io/v3/")
        .build()

    var service = retrofit.create(ApiService::class.java)

    fun getRequest(path: String, callback: CustomCallback) {
        val call = service.getRequest(path)
        call.enqueue(onCallBack(callback))
    }


    private fun onCallBack(callback: CustomCallback) = object : Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            d("onFailure", "${t.message}")
            callback.onFailed(t.message.toString())
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            d("response", "${response.body()}")

            callback.onSuccess(response.body().toString())
        }
    }

    interface ApiService {
        @GET("{path}")
        fun getRequest(@Path("path") path: String): Call<String>
    }
}