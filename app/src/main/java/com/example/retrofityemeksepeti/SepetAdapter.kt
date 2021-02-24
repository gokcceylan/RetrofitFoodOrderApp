package com.example.retrofityemeksepeti

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sepet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SepetAdapter(var mContext: Context
                   , var sepetListe:ArrayList<Sepettekiler>
                   ) : RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>() { //CardTasarimTutucu is added
    // Adapter class is for handling card operations

    //, var sdi: SepettekilerDAOInterface
    private lateinit var sdi: SepettekilerDAOInterface


    inner class CardTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim){
        var sepet_card : CardView
        var satir_ad : TextView
        var satir_fiyat : TextView
        var satir_toplam_fiyat : TextView
        var satir_adet : TextView
        var satir_resim : ImageView
        var satir_resim_sil : ImageView
        // the CardView attributes need to be declared as such
        // and initialized inside 'init'
        // otherwise card operations cannot be done

        init{
            sepet_card = tasarim.findViewById(R.id.sepet_card)
            satir_ad = tasarim.findViewById(R.id.satir_ad)
            satir_fiyat = tasarim.findViewById(R.id.satir_fiyat)
            satir_toplam_fiyat = tasarim.findViewById(R.id.satir_toplam_fiyat)
            satir_adet = tasarim.findViewById(R.id.satir_adet)
            satir_resim = tasarim.findViewById(R.id.satir_resim)
            satir_resim_sil = tasarim.findViewById(R.id.satir_resim_sil)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.sepet_card_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)
        // connects the design and the code
    }

    override fun getItemCount(): Int {
        return sepetListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        // position needs to be gotten and a Sepettekiler instance needs to be created to set the design items according to the web services
        val sepet = sepetListe.get(position)

        holder.satir_ad.text = sepet.yemek_adi
        holder.satir_fiyat.text = "Birim Fiyat: ${sepet.yemek_fiyat} ₺"
        holder.satir_adet.text = "Adet: ${sepet.yemek_siparis_adet}"
        holder.satir_toplam_fiyat.text = "${sepet.yemek_fiyat * sepet.yemek_siparis_adet} ₺"
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
        Picasso.get().load(url).into(holder.satir_resim)

        holder.satir_resim_sil.setOnClickListener {
            sepetSil(sepet.yemek_id)
            Toast.makeText(mContext, "${sepet.yemek_adi} sepetten silindi.", Toast.LENGTH_SHORT).show()
        }

    }

    fun sepetSil(yemek_id:Int){
        sdi = ApiUtils.getSepettekilerDAOInterface()
        sdi.sepetSil(yemek_id).enqueue(object: Callback<CRUDCevap>{

            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {
                if(response != null){
                    tumSepet()
                }
            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {

            }

        })
    }

    fun tumSepet(){
        sdi = ApiUtils.getSepettekilerDAOInterface()
        sdi.tumSepet().enqueue(object: Callback<SepettekilerCevap> {
            override fun onResponse(call: Call<SepettekilerCevap>?, response: Response<SepettekilerCevap>?) {
                if(response != null){
                    sepetListe = response.body().sepettekiler

                    notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<SepettekilerCevap>?, t: Throwable?) {

            }
        })
    }
}