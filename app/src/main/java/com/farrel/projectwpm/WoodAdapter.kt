package com.farrel.projectwpm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_wood.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class WoodAdapter(val context: Context, val list: List<WoodContract>)
    : RecyclerView.Adapter<WoodAdapter.ViewHolder>(){

    lateinit var itemview: View

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        itemview = LayoutInflater.from(context).inflate(R.layout.item_wood, p0, false)
        return ViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(list[p1])
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(studentContract: WoodContract) {
            itemView.is_tv_student_name.text = studentContract.name
            itemView.is_tv_student_age.text = studentContract.namaBarang.toString()
            itemView.tvAlamat.text = studentContract.alamat.toString()

            itemView.onClick {
                itemView.context.startActivity(itemView.context.intentFor<DetailWoodActivity>(
                    "student" to studentContract))
            }
        }

    }

}