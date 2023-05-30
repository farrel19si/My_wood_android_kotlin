package com.farrel.projectwpm

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_wood.*
import org.jetbrains.anko.db.delete

class DetailWoodActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var wood: WoodContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wood)


        wood = intent.getParcelableExtra<WoodContract>("student")!!
        Log.d("STUDENT", wood.toString())
        // mengambil data
        dsa_tv_name.text = wood.name
        dsa_tv_age.text = wood.namaBarang
        dsa_tv_address.text = wood.alamat
        dsa_tv_major.text = wood.nohp

        dsa_btn_delete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            dsa_btn_delete -> deleteData(wood.id)
        }
    }

    private fun deleteData(id: Long?) {
        database.use {
            delete(WoodContract.TABLE_WOOD, "${WoodContract.ID} = {id}", "id" to id!!.toInt())
        }
        finish()
    }


}
