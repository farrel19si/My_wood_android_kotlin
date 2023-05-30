package com.farrel.projectwpm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

//TODO Buat dan atur kelas MyDatabaseHelper

class MyDatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "database_vaksin.db", null, 1) {

    companion object {
        private var instance: MyDatabaseHelper? = null

        fun getInstance(context: Context): MyDatabaseHelper {
            if (instance == null) {
                instance = MyDatabaseHelper(context)
            }
            return instance as MyDatabaseHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Buat tabel pada database
        db?.createTable(
            WoodContract.TABLE_WOOD,
            true,
            WoodContract.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            WoodContract.NAME to TEXT,
            WoodContract.NAMABARANG to TEXT,
            WoodContract.ALAMAT to TEXT,
            WoodContract.PHOTO to TEXT,
            WoodContract.NOHP to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(WoodContract.TABLE_WOOD, true)
    }
}

val Context.database: MyDatabaseHelper
    get() = MyDatabaseHelper.getInstance(applicationContext)