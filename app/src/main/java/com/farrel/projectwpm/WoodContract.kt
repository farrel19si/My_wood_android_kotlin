package com.farrel.projectwpm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//TODO 7 Buat sebuah kelas model

@Parcelize
data class WoodContract(
    val id: Long?,
    val name: String,
    val namaBarang: String,
    val alamat: String,
    val photo: String?,
    val nohp: String
) : Parcelable {
    companion object{
        const val TABLE_WOOD = "table_wood"
        const val ID = "id"
        const val NAME = "name"
        const val NAMABARANG = "namaBarang"
        const val ALAMAT = "alamat"
        const val PHOTO = "photo"
        const val NOHP = "nohp"
    }
}