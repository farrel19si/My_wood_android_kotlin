package com.farrel.projectwpm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListActivity : AppCompatActivity(){

    var adapter: WoodAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val list = getListDataStudent()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = WoodAdapter(this, list)

        main_rv_list_data.layoutManager = layoutManager
        main_rv_list_data.adapter = adapter
    }
    private fun getListDataStudent(): List<WoodContract> {
        //ambil data seluruh table vaksin
        var listData: List<WoodContract>? = null
        database.use {
            val result = select(WoodContract.TABLE_WOOD)
            listData = result.parseList(classParser<WoodContract>())
        }
        return listData!!
    }

    override fun onResume() {
        super.onResume()
        val listRefresh = getListDataStudent()
        adapter = WoodAdapter(this, listRefresh)
        adapter?.notifyDataSetChanged()
        main_rv_list_data.adapter = adapter
    }
}