package com.farrel.projectwpm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.farrel.projectwpm.databinding.ActivityBarangBinding

class BarangActivity : AppCompatActivity() {
    private lateinit var barangAdapter:BarangRecyclerAdapter
    private lateinit var binding: ActivityBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang)
        binding= ActivityBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        tambahDataset()
    }
    private fun tambahDataset(){
    val data=SumberData.buatsetData()
    barangAdapter.submitList(data)
    }
    private fun initRecyclerView(){
        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@BarangActivity)
            val spacingAtas= DekorasiSpasiGambar(20)
            addItemDecoration(spacingAtas)
            barangAdapter= BarangRecyclerAdapter()
            adapter=barangAdapter
        }
    }
}
