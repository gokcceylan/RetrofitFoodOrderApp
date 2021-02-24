package com.example.retrofityemeksepeti

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sepet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SepetActivity : AppCompatActivity() {
    private lateinit var s_adapter:SepetAdapter
    private lateinit var sdi: SepettekilerDAOInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepet)
        // Replace setContentView(R.layout.product_list) with the line below
        //setContentView(R.layout.sepet_list_host)

        toolbarSepet.title = "Sepet"
        toolbarSepet.setTitleTextColor(Color.BLACK)
        setSupportActionBar(toolbarSepet)

        rvSepet.setHasFixedSize(true) // so that it'll look more put together
        rvSepet.layoutManager = LinearLayoutManager(this)

        sdi = ApiUtils.getSepettekilerDAOInterface()
        tumSepet() // if volley wasn't used, this part would contain the val k = ... codes to create the array list and send it to the adapter

    }
    fun tumSepet(){
        sdi.tumSepet().enqueue(object: Callback<SepettekilerCevap> {
            override fun onResponse(call: Call<SepettekilerCevap>?, response: Response<SepettekilerCevap>?) {
                if(response != null){
                    val sepetListe = response.body().sepettekiler

                    s_adapter = SepetAdapter(this@SepetActivity, sepetListe)
                    rvSepet.adapter = s_adapter

                }
            }

            override fun onFailure(call: Call<SepettekilerCevap>?, t: Throwable?) {

            }
        })
    }


}