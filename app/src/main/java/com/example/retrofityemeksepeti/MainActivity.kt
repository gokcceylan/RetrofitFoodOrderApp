package com.example.retrofityemeksepeti

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var y_adapter:YemeklerAdapter
    private lateinit var ydi: YemeklerDAOInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMenu.title = "Menü"
        toolbarMenu.setTitleTextColor(Color.BLACK)
        setSupportActionBar(toolbarMenu)

        menuRv.setHasFixedSize(true)
        menuRv.layoutManager = LinearLayoutManager(this)

        ydi = ApiUtils.getYemeklerDAOInterface()

        tumYemekler()

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity,SepetActivity::class.java))
        }

    }
    override fun onBackPressed() {
        val yeniIntent = Intent(Intent.ACTION_MAIN)
        yeniIntent.addCategory(Intent.CATEGORY_HOME)
        yeniIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(yeniIntent)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_arama_menu,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Gönderilen Arama Sonucu",query)
        aramaYap(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Harf Girdikçe Sonuc",newText)
        aramaYap(newText)
        return true
    }

    fun tumYemekler(){
        ydi.tumYemekler().enqueue(object: Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>?) {
                if(response != null){
                    val liste = response.body().yemekler

                    y_adapter = YemeklerAdapter(this@MainActivity, liste)
                    menuRv.adapter = y_adapter
                }

            }

            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {

            }
        })
    }
    fun aramaYap(aramaKelime : String){
        ydi.aramaYap(aramaKelime).enqueue(object: Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>?) {
                if(response != null){
                    val liste = response.body().yemekler

                    y_adapter = YemeklerAdapter(this@MainActivity, liste)
                    menuRv.adapter = y_adapter
                }
            }

            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {

            }
        })
    }
}