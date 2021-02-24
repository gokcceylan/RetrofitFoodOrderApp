package com.example.retrofityemeksepeti

class ApiUtils {

    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getYemeklerDAOInterface():YemeklerDAOInterface{
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDAOInterface::class.java)
        }
        fun getSepettekilerDAOInterface():SepettekilerDAOInterface{
            return RetrofitClient.getClient(BASE_URL).create(SepettekilerDAOInterface::class.java)
        }
    }
}