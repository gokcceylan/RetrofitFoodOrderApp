package com.example.retrofityemeksepeti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CRUDCevap(@SerializedName("success") @Expose var success:Int
                , @SerializedName("message") @Expose var message:String) : Serializable{
}
// since this class' correspondence isn't an array, no second class is necessary such as "CRUD"
// CRUD stands for create, read, update, delete