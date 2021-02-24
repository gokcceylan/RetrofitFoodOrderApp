package com.example.retrofityemeksepeti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class YemeklerCevap (@SerializedName("yemekler") @Expose var yemekler : ArrayList<Yemekler>
                     , @SerializedName("success") @Expose var success:Int) : Serializable {
}