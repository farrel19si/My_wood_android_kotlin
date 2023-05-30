package com.farrel.projectwpm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farrel.projectwpm.databinding.DetailBarangBinding

class DetailBarang : AppCompatActivity() {
    private lateinit var binding: DetailBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailBarangBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnPesan.setOnClickListener {
            startActivity(Intent(this, Form_Pesan::class.java))
        }

        if (intent.hasExtra("nama")) {
            val nama:String = this.intent.getStringExtra("nama").toString()
            val gambar:String = this.intent.getStringExtra("gambar").toString()
            val harga:String = this.intent.getStringExtra("harga").toString()
            setDetail(gambar, nama, harga)
        }

    }

    fun setDetail(gambar:String,nama:String,harga:String){
        val requestOp= RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        binding.namaDetail.text = nama
        Glide.with(this).load(gambar).apply(requestOp).centerCrop()
            .into(binding.gambarDetail)

        binding.hargaDetail.text = harga
        Glide.with(this).load(gambar).apply(requestOp).centerCrop()
            .into(binding.gambarDetail)

    }
   }