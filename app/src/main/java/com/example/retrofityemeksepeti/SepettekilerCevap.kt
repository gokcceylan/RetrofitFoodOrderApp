package com.example.retrofityemeksepeti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SepettekilerCevap (@SerializedName("sepet_yemekler") @Expose var sepettekiler:ArrayList<Sepettekiler>
                        , @SerializedName("success") @Expose var success:Int) : Serializable {
}