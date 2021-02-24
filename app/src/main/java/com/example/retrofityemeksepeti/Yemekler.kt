package com.example.retrofityemeksepeti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Yemekler(@SerializedName("yemek_id") @Expose var yemek_id:Int,
               @SerializedName("yemek_adi") @Expose var yemek_adi:String,
               @SerializedName("yemek_resim_adi") @Expose var yemek_resim_adi:String,
               @SerializedName("yemek_fiyat") @Expose var yemek_fiyat:Int): Serializable {
// this class NEEDS to extend Serializable so that its info can be transferred from one activity to another
}
//JSON response class