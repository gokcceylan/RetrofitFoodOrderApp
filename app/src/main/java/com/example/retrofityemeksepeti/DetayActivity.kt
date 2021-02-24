package com.example.retrofityemeksepeti

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detay.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetayActivity : AppCompatActivity() {
    private lateinit var yemek: Yemekler
    private lateinit var sdi: SepettekilerDAOInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        toolbarDetay.setTitle("Yemek Detay")
        toolbarDetay.setTitleTextColor(Color.BLACK)
        setSupportActionBar(toolbarDetay)

        sdi = ApiUtils.getSepettekilerDAOInterface()

        yemek = intent.getSerializableExtra("nesne") as Yemekler

        detay_ad.setText(yemek.yemek_adi)
        detay_fiyat.setText(yemek.yemek_fiyat.toString() + " ₺")
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Picasso.get().load(url).into(detay_resim)

        buttonEkle.setOnClickListener {
            val yemekAdet = detay_adet.text.toString() // no need to check because inputType is set to numbers (see activity_detay.xml)
            // only the yemekAdet is gotten from the user. others are yemek object attributes and are already known
            sepetEkle(yemek.yemek_id, yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat, yemekAdet.toInt())
        }

    }
    fun sepetEkle(yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int){
        sdi.sepetEkle(yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet).enqueue(object: Callback<CRUDCevap> {

            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {
                if(response != null){
                    startActivity(Intent(this@DetayActivity, SepetActivity::class.java)) // when the food is ordered, cart page is opened
                    finish() // so that this page is skipped when clicked on the 'back' button
                }
            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {

            }

        })
    }
}
