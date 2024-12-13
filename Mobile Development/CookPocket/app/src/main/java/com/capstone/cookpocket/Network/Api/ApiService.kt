package com.capstone.cookpocket.Network.Api

import com.capstone.cookpocket.Network.Response.CheckoutRequest
import com.capstone.cookpocket.Network.Response.CheckoutResponse
import com.capstone.cookpocket.Network.Response.CookPocketResponse
import com.capstone.cookpocket.Network.Response.LoginResponse
import com.capstone.cookpocket.Network.Response.RegisterResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
interface ApiService {

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("noTelp") noTelp: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("products/category/makanan-berat?id_category=1")
    suspend fun getMakananBerat(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ): CookPocketResponse

    @GET("products/category/makanan-sehat?id_category=3")
    suspend fun getMakananSehat(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): CookPocketResponse

    @GET("products/category/makanan-tradisional?id_category=2")
    suspend fun getMakananTradisional(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): CookPocketResponse

    @GET("products/search")
    suspend fun searchProducts(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ): CookPocketResponse

//    @POST("checkout")
//    suspend fun checkout(
//        @Body checkoutRequest: CheckoutRequest
//    ): CheckoutResponse

    // Endpoint Checkout
    @POST("checkout")
    fun checkout(
        @Body checkoutRequest: CheckoutRequest
    ): Call<CheckoutResponse>

    @GET("user/me")
    suspend fun getUserData(
        @Header("Authorization") token: String
    ): LoginResponse
}
