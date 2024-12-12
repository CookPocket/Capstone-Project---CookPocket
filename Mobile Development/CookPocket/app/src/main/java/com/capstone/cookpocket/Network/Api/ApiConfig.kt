package com.capstone.cookpocket.Network.Api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    private const val BASE_URL = "https://cookpocket.et.r.appspot.com/api/"

    fun getApiService(token: String? = null): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

        // Tambahkan Authorization Header jika token ada
        if (token != null) {
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(requestHeaders)
            }
            clientBuilder.addInterceptor(authInterceptor)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}
