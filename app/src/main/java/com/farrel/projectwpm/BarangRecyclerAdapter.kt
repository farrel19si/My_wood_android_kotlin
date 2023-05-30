package com.farrel.projectwpm

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farrel.projectwpm.databinding.LayoutListBarangBinding

class BarangRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items:List<ListObjBarang> =ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val binding = LayoutListBarangBinding.inflate(
            LayoutInflater.from(parent.context)
        ,parent,false)
        return BarangViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BarangViewHolder -> {
                holder.bind(items.get(position))
                holder.klik.setOnClickListener {
                    holder.kalau_diklik(items.get(position))
                }
            }
        }
    }
        fun submitList(listBarang: List<ListObjBarang>) {
            items = listBarang
        }

       override fun getItemCount(): Int {
            return items.size
        }

        class BarangViewHolder constructor(val binding: LayoutListBarangBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val gambar_barang: ImageView = binding.gambarBarang
            val nama_barang: TextView = binding.namaBarang
            val txtharga : TextView = binding.txtharga
            val klik: RelativeLayout = binding.rlKlik

            fun bind(listObjBarang: ListObjBarang) {
                nama_barang.setText(listObjBarang.nama)
                txtharga.setText(listObjBarang.harga)
                val requestOp = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOp)
                    .load(listObjBarang.gambar).
                    into(gambar_barang)
            }

            fun kalau_diklik(get: ListObjBarang){
                Toast.makeText(itemView.context,"Kamu memilih:${get.nama}",
                    Toast.LENGTH_SHORT)
                    .show()

                val intent= Intent(itemView.context,DetailBarang::class.java)
                intent.putExtra("nama",get.nama)
                intent.putExtra("harga",get.harga)
                intent.putExtra("gambar",get.gambar)
                itemView.context.startActivity(intent)

            }
            }


    }