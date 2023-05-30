package com.farrel.projectwpm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.farrel.projectwpm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.card1.setOnClickListener{
            startActivity(Intent(this,MapsActivity::class.java))
        }
        binding.card2.setOnClickListener{
            startActivity(Intent(this,BarangActivity::class.java))
        }
        binding.card3.setOnClickListener{
            startActivity(Intent(this,Form_Pesan::class.java))
        }
    }



}