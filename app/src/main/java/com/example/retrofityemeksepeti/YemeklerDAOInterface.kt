package com.example.retrofityemeksepeti

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDAOInterface {

    @GET("yemekler/tum_yemekler.php")
    fun tumYemekler(): Call<YemeklerCevap>

    @POST("yemekler/tum_yemekler_arama.php")
    @FormUrlEncoded
    fun aramaYap(@Field("yemek_adi") yemek_adi:String): Call<YemeklerCevap>

}