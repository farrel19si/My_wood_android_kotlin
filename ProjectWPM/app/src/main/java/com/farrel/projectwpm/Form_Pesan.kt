package com.farrel.projectwpm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.farrel.projectwpm.databinding.ActivityFormPesanBinding
import kotlinx.android.synthetic.main.activity_form_pesan.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

class Form_Pesan : AppCompatActivity() {
    private lateinit var binding: ActivityFormPesanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form_pesan)
        binding.activity = this




        asa_tv_choose_study.onClick {
            val jurusan = listOf("Kasur","Kursi Teras","Meja Makan","Vas Bunga")
            selector("Nama Barang",jurusan) { dialog, i ->
                asa_tv_major.setText(jurusan[i])
            }
        }

        asa_btn_save.onClick {
            if (!validation()){
                return@onClick
            }
            insertDatabase()
        }
    }
    fun user(view: View) {
        var intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
    private fun insertDatabase() { //masukkan database
        database.use {
            insert(WoodContract.TABLE_WOOD,
                WoodContract.NAME to asa_edt_name.text.toString(),
                WoodContract.ALAMAT to asa_edt_age.text.toString(),
                WoodContract.NOHP to asa_edt_address.text.toString(),
                WoodContract.PHOTO to null,
                WoodContract.NAMABARANG to asa_tv_major.text.toString()
            )

            toast("Anda Berhasil Memesan Barang!")
        }
    }

    private fun validation(): Boolean{
        when {
            asa_edt_name.text.toString().isBlank() -> {
                asa_edt_name.requestFocus()
                asa_edt_name.error = "Tidak boleh kosong"
                return false
            }
            asa_edt_age.text.toString().isBlank() -> {
                asa_edt_age.requestFocus()
                asa_edt_age.error = "Tidak boleh kosong"
                return false
            }
            asa_edt_address.text.toString().isBlank() -> {
                asa_edt_address.requestFocus()
                asa_edt_address.error = "Tidak boleh kosong"
                return false
            }
            asa_tv_major.text.toString().isBlank() -> {
                asa_tv_major.requestFocus()
                asa_tv_major.error = "Tidak boleh kosong"
                return false
            }
            else -> return true
        }

    }
}